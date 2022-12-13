package com.mubeen.multibankgroupassessment.view.activity

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mubeen.multibankgroupassessment.R
import com.mubeen.multibankgroupassessment.data.OrderInteractor
import com.mubeen.multibankgroupassessment.data.model.Order
import com.mubeen.multibankgroupassessment.databinding.ActivityMainBinding
import com.mubeen.multibankgroupassessment.domain.OrderState
import com.mubeen.multibankgroupassessment.enums.MyEnums
import com.mubeen.multibankgroupassessment.presenter.MainPresenter
import com.mubeen.multibankgroupassessment.view.MainView
import com.mubeen.multibankgroupassessment.view.adapter.OrderListAdapter
import com.mubeen.multibankgroupassessment.view.dialog.OrderDetailDialog
import com.mubeen.multibankgroupassessment.view.listeners.OrderAdapterInteraction

class MainActivity : AppCompatActivity(),MainView,View.OnClickListener,OrderAdapterInteraction {

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private lateinit var binding:ActivityMainBinding

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: OrderListAdapter

    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListener()
        setupPresenter()
        setupViews()

    }

    private fun setupListener() {
        binding.placeOrderBtn.setOnClickListener(this)
    }

    private fun setupPresenter() {
        presenter = MainPresenter(OrderInteractor())
        presenter.bind(this)

    }

    override fun render(state: OrderState) {
        when (state) {
            is OrderState.LoadingState -> renderLoadingState()
            is OrderState.DataState -> renderDataState(state)
            is OrderState.ErrorState -> renderErrorState(state)
            else -> {}
        }
    }

    private fun renderLoadingState() {
        binding.orderRv.isEnabled = false
        Log.d("TAG", "LOADING");
    }

    private fun renderDataState(dataState: OrderState.DataState) {
        binding.orderRv.apply {
            isEnabled = true
            (adapter as OrderListAdapter).setOrders(dataState.data)
        }
    }

    private fun renderErrorState(dataState: OrderState.ErrorState) {
        Toast.makeText(this@MainActivity, dataState.data, Toast.LENGTH_LONG).show()
    }

    private fun setupViews() {
        binding.orderRv.layoutManager = LinearLayoutManager(this)
        adapter = OrderListAdapter(mutableListOf(),this)
        binding.orderRv.adapter = adapter
    }

    private fun openAlertDialog(orderNextState: MyEnums, order: Order) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Update Order State")
        alertDialogBuilder.setMessage("Do you want to change order state to ${orderNextState.toString()}")
        alertDialogBuilder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            order.state = orderNextState.toString()
            presenter.updateOrderState(order)
        }
        alertDialogBuilder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
        }
        alertDialog = alertDialogBuilder.create()
        alertDialog?.show()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.placeOrderBtn -> {
                presenter.placeOrder()
            }
        }
    }

    override fun onStateClick(order: Order) {
        when(order.state){
            MyEnums.New.toString() -> {
                openAlertDialog(MyEnums.Preparing,order)
            }
            MyEnums.Preparing.toString() ->{
                openAlertDialog(MyEnums.Ready, order)
            }
            MyEnums.Ready.toString() -> {
                openAlertDialog(MyEnums.Delivered, order)
            }
            MyEnums.Delivered.toString() -> {
                Toast.makeText(this@MainActivity, "Order is delivered! It will automatically remove from list soon", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemClick(order: Order) {
        OrderDetailDialog(order).show(supportFragmentManager,"OrderDetailDialog")
    }

}