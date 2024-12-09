package com.mobileProgramming.travelaccountbook.Member

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.mobileProgramming.travelaccountbook.R

class LoginFragment : Fragment() {
    private lateinit var editTextId: EditText
    private lateinit var editTextPw: EditText
    private lateinit var countrySelectTextView: TextView
    private lateinit var buttonLogin: Button
    private lateinit var textViewSignup: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        editTextId = view.findViewById(R.id.editTextId)
        editTextPw = view.findViewById(R.id.editTextPw)
        countrySelectTextView = view.findViewById(R.id.textViewCountrySelect)
        buttonLogin = view.findViewById(R.id.buttonLogin)
        textViewSignup = view.findViewById(R.id.textViewSignup)

        // 국가 선택 드롭다운 메뉴 설정
        val countryList = arrayOf("대한민국", "미국", "일본", "중국", "유럽연합")
        countrySelectTextView.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("국가 선택")
                .setItems(countryList) { _, which ->
                    countrySelectTextView.text = countryList[which]
                }
                .show()

        }

        buttonLogin.setOnClickListener {
            val enteredId = editTextId.text.toString()
            val enteredPw = editTextPw.text.toString()
            val selectedCountry = countrySelectTextView.text.toString()

            // 비밀번호 확인 => 하단에 DB랑 통신하는 메서드 정의해놨어용
            val isPasswordCorrect = checkPasswordWithBackend(enteredId, enteredPw)
            if (isPasswordCorrect) {
                // 로그인 성공
                // 계좌 화면으로 넘어가는 액션
                findNavController().navigate(R.id.action_loginFragment_to_accountUi)

            } else {
                // 로그인 실패 시 초기화면으로
                editTextId.text.clear()
                editTextPw.text.clear()
                countrySelectTextView.text = context?.getString(R.string.country_select)
            }
        }

        // 회원가입 텍스트 클릭 이벤트 임시 함수
        textViewSignup.setOnClickListener {

            // 회원가입으로 넘어가는 액션
            findNavController().navigate(R.id.action_loginFragment_to_signUp)
        }

        return view
    }

    // 비밀번호 확인 임시 함수 = 백엔드 나오면 수정 예정
    private fun checkPasswordWithBackend(id: String, password: String): Boolean {
//        return false
        return true
    }
}