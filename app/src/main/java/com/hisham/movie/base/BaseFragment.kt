package com.hisham.movie.base
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

import com.hisham.movie.BR
import com.hisham.movie.data.prefs.PreferenceManager
import org.koin.android.ext.android.inject

abstract class BaseFragment<DATA_BINDING: ViewDataBinding,VIEW_MODEL:BaseViewModel>: Fragment() {
    lateinit var viewDataBinding: DATA_BINDING
    private var baseViewModel: VIEW_MODEL? = null
    val preferenceManager: PreferenceManager by inject()
    var layoutView:View?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        performDataBinding(inflater,container)

        return viewDataBinding.root
    }

    private fun performDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        viewDataBinding= DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        this.baseViewModel=baseViewModel?:getViewModel()
        viewDataBinding.apply {
            setVariable(getBindingVariable(),baseViewModel)
            setLifecycleOwner(viewLifecycleOwner)
            executePendingBindings()
        }
    }

    /**
     * @return layout resource id
     */
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
    open fun getBindingVariable(): Int = BR.viewModel

}