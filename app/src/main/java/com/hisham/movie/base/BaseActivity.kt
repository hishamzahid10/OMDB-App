package com.hisham.movie.base


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hisham.movie.BR
import com.hisham.movie.utils.extentions.*
import org.koin.android.ext.android.inject


@Suppress("DEPRECATION")
abstract class BaseActivity<DATA_BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel> : AppCompatActivity() {

    lateinit var viewDataBinding: DATA_BINDING
    private var baseViewModel:VIEW_MODEL?=null
    val preferenceManager: com.hisham.movie.data.prefs.PreferenceManager by inject()
     var layoutView:View?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setupUI(viewDataBinding.root)
    }

    private fun performDataBinding() {
        viewDataBinding= DataBindingUtil.setContentView(this,getLayoutId())
        this.baseViewModel=baseViewModel?:getViewModel()
        viewDataBinding.apply {
            setVariable(getBindingVariable(),baseViewModel)
            setLifecycleOwner(this@BaseActivity)
            executePendingBindings()
        }
    }

    /** @return layout resource id*/

    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): VIEW_MODEL

    /**
     * Override for set binding variable
     *
     * @return variable id
     */

    open fun getBindingVariable(): Int= BR.viewModel

}

