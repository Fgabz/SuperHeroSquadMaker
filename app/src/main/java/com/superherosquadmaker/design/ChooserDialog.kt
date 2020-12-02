package com.superherosquadmaker.design

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.superherosquadmaker.R
import kotlinx.android.synthetic.main.dialog_chooser.*

class ChooserDialog(
    private val title: String? = null,
    private val description: String? = null,
    private val actionList: List<Action>,
    private val cancelLabel: String? = null,
    private val onCancelListener: ((ChooserDialog) -> Unit)? = null
) : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AlertBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.dialog_chooser, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        if (title == null && description == null) {
            chooserHeader.visibility = View.GONE
        } else {
            if (title == null) {
                chooserTitle.visibility = View.GONE
            } else {
                chooserTitle.text = title
            }
            if (description == null) {
                chooserMessage.visibility = View.GONE
            } else {
                chooserMessage.text = description
            }
        }


        cancelLabel?.let {
            negativeButton.visibility = View.VISIBLE
            negativeButton.text = it

            negativeButton.setOnClickListener {
                onCancelListener?.invoke(this)
            }
        }

        actionList.forEach { action ->
            val actionLayout = layoutInflater.inflate(R.layout.item_chooser_action, view as ViewGroup, false) as TextView

            actionLayout.text = action.label
            actionLayout.setTextColor(action.color)
            actionLayout.setOnClickListener {
                close()
                action.onClickListener.invoke()
            }

            actionsLayout.addView(actionLayout)
        }

        dialog?.window?.decorView?.findViewById<View>(com.google.android.material.R.id.touch_outside)?.setOnClickListener {
            onCancelListener?.invoke(this)
        }
    }

    fun close() {
        dismiss()
    }
}

data class Action(val label: String, val color: Int, val onClickListener: () -> Unit)

class ChooserBuilder(private val activity: FragmentActivity) {

    var title: String? = null
    var description: String? = null
    private val actionList: MutableList<Action> = mutableListOf()
    private var cancelLabel: String? = null
    private var onCancelListener: ((ChooserDialog) -> Unit)? = null

    fun addAction(@StringRes labelRes: Int, color: Int, onClickListener: () -> Unit) {
        actionList.add(
            Action(
                activity.getString(labelRes),
                ContextCompat.getColor(activity, color),
                onClickListener
            )
        )
    }

    fun setOnCancelListener(labelRes: Int, listener: (ChooserDialog) -> Unit) {
        cancelLabel = activity.getString(labelRes)
        onCancelListener = listener
    }

    fun show() {
        ChooserDialog(title, description, actionList, cancelLabel, onCancelListener).show(
            activity.supportFragmentManager,
            ChooserDialog::class.java.name
        )
    }
}

fun showChooserDialog(activity: FragmentActivity, block: ChooserBuilder.() -> Unit) = ChooserBuilder(activity).apply(block).show()