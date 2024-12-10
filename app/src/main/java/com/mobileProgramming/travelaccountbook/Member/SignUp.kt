package com.example.yourapp
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.mobileProgramming.travelaccountbook.Member.DatabaseProvider
import com.mobileProgramming.travelaccountbook.Member.User
import com.mobileProgramming.travelaccountbook.databinding.FragmentSignUpBinding
import kotlinx.coroutines.launch

class SignUp : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = DatabaseProvider.getDatabase(requireContext())
        val userDao = db.userDao()

        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val passwordConfirm = binding.etPasswordConfirm.text.toString()
            val age = binding.etAge.text.toString().toIntOrNull() ?: 0
            val gender = when (binding.rgGender.checkedRadioButtonId) {
                binding.rbMale.id -> "남자"
                binding.rbFemale.id -> "여자"
                else -> ""
            }

            if (password != passwordConfirm) {
                Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(
                name = name,
                email = email,
                password = password,
                gender = gender,
                age = age
            )

            lifecycleScope.launch {
                userDao.insertUser(user)
                Toast.makeText(context, "회원가입 완료", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
