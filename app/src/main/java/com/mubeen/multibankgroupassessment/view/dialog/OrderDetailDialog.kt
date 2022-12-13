package com.mubeen.multibankgroupassessment.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mubeen.multibankgroupassessment.R
import com.mubeen.multibankgroupassessment.Utils
import com.mubeen.multibankgroupassessment.data.model.Order
import com.mubeen.multibankgroupassessment.databinding.OrderDetailDialogBinding

class OrderDetailDialog(private val order: Order) : DialogFragment() {

    private var _binding: OrderDetailDialogBinding? = null
    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = OrderDetailDialogBinding.inflate(LayoutInflater.from(context))

        binding.orderNo.text = "Order - ${order.id}"
        binding.orderDateTime.text = "Order Time: ${Utils.getDate(order.date)}"

        binding.closeBtn.setOnClickListener(View.OnClickListener {
            this.dismiss()
        })
        return binding.root

    }
}