package vn.nguyendinhkien.appcommon.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vn.nguyendinhkien.appcommon.domain.usecase.authenication.LoginUseCase
import vn.nguyendinhkien.appcommon.domain.usecase.authenication.RegistrationUseCase
import vn.nguyendinhkien.appcommon.domain.usecase.notes.NotesUseCase
import vn.nguyendinhkien.appcommon.presentation.SharedViewModel
import vn.nguyendinhkien.appcommon.presentation.base.ui.QuickViewModel
import vn.nguyendinhkien.appcommon.presentation.ui.home.HomeViewModel
import vn.nguyendinhkien.appcommon.presentation.ui.list.ListViewAdapter
import vn.nguyendinhkien.appcommon.presentation.ui.list.RecyclerViewDemoViewModel
import vn.nguyendinhkien.appcommon.presentation.ui.login.LoginViewModel
import vn.nguyendinhkien.appcommon.presentation.ui.registration.RegistrationViewModel

val appModule = module {
    viewModel { HomeViewModel() }
    viewModel { RecyclerViewDemoViewModel(NotesUseCase(get())) }
    viewModel { LoginViewModel(LoginUseCase(get())) }
    viewModel { RegistrationViewModel(RegistrationUseCase(get())) }
    viewModel { SharedViewModel() }
    viewModel { QuickViewModel() }

    single { ListViewAdapter() }
}