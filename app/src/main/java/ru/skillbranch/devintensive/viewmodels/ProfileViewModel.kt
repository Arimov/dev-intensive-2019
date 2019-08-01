package ru.skillbranch.devintensive.viewmodels

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.repositories.PreferencesRepository

class ProfileViewModel : ViewModel() {
    private val repository: PreferencesRepository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    private val appTheme = MutableLiveData<Int>()
    private val repositoryError = MutableLiveData<Boolean>()
    private val isRepoError = MutableLiveData<Boolean>()

    init {
        Log.d("M_ProfileViewModel", "init view model")
        profileData.value = repository.getProfile()
        appTheme.value = repository.getAppTheme()
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun getProfileData(): LiveData<Profile> = profileData

    fun getTheme(): LiveData<Int> = appTheme

    fun getRepositoryError(): LiveData<Boolean> = repositoryError

    fun getIsRepoError(): LiveData<Boolean> = isRepoError

    fun saveProfileData(profile: Profile) {
        repository.saveProfile(profile)
        profileData.value = profile
    }

    fun switchTheme() {
        if (appTheme.value == AppCompatDelegate.MODE_NIGHT_YES) {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_NO
        } else {
            appTheme.value = AppCompatDelegate.MODE_NIGHT_YES
        }
        repository.saveAppTheme(appTheme.value!!)
    }

    fun onRepositoryChanged(repository: String) {
        repositoryError.value = isValidateRepository(repository)
    }

    fun onRepoEditCompleted(isError: Boolean) {
        isRepoError.value = isError
    }

    private fun isValidateRepository(repo: String): Boolean {
        val regexStr = "^(https://)?(www.)?(github.com/)(?!(${getRegexExceptions()})(?=/|\\\$))(?![\\\\W])(?!\\\\w+[-]{2})[a-zA-Z0-9-]+(?<![-])(/)?\$"
        val regex = Regex(regexStr)
        return (repo.isNotEmpty() && !regex.matches(repo))
    }

    private fun getRegexExceptions(): String {
        val exceptions = arrayOf(
            "enterprise", "features", "topics", "collections",
            "trending", "events", "marketplace", "pricing", "nonprofit",
            "customer-stories", "security", "login", "join"
        )
        return exceptions.joinToString("|\\b", "\\b")
    }

    /*private fun isValidateRepository(repo: String): Boolean = repo.matches(
        Regex(
            "^(http(s){0,1}:\\/\\/){0,1}(www.){0,1}github.com\\/[A-z\\d](?:[A-z\\d]|-(?=[A-z\\d])){0,38}\$",
            RegexOption.IGNORE_CASE
        )
    ) &&
            !repo.matches(
                Regex(
                    "^.*(" +
                            "\\/enterprise|" +
                            "\\/features|" +
                            "\\/topics|" +
                            "\\/collections|" +
                            "\\/trending|" +
                            "\\/events|" +
                            "\\/marketplace" +
                            "|\\/pricing|" +
                            "\\/nonprofit|" +
                            "\\/customer-stories|" +
                            "\\/security|" +
                            "\\/login|" +
                            "\\/join)\$", RegexOption.IGNORE_CASE
                )
            )*/
}