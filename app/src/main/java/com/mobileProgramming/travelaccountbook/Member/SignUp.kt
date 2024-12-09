package com.example.yourapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mobileProgramming.travelaccountbook.databinding.FragmentSignUpBinding

class SignUp : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!! // Null 안전성을 위한 getter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 데이터 바인딩 객체 초기화
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root // 바인딩 객체의 root 반환
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 버튼 클릭 이벤트 설정
        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString()
            val id = binding.etId.text.toString()
            val password = binding.etPassword.text.toString()
            val passwordConfirm = binding.etPasswordConfirm.text.toString()
            val age = binding.etAge.text.toString()
            val email = binding.etEmail.text.toString()

            val gender = when (binding.rgGender.checkedRadioButtonId) {
                binding.rbMale.id -> "남자"
                binding.rbFemale.id -> "여자"
                else -> ""
            }

            if (password != passwordConfirm) {
                Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(
                context,
                "회원가입 완료: 이름 $name, 아이디 $id, 성별 $gender, 나이 $age, 이메일 $email",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 뷰가 파괴되면 바인딩 객체 해제
        _binding = null
    }
}
