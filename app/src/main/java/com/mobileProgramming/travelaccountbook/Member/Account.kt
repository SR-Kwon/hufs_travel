package com.mobileProgramming.travelaccountbook.Member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobileProgramming.travelaccountbook.R

class AccountUi : Fragment() {

    private val transactionList = mutableListOf<String>()
//    private val transactionAdapter = TransactionAdapter(transactionList)

    private var spentUSD = 0
    private var spentKRW = 0
    private var remainingUSD = 100
    private var remainingKRW = 139300
    private val exchangeRate = 1393

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        // Initialize UI components
        val editTextAmount: EditText = view.findViewById(R.id.editTextAmount)
        val btnDeposit: Button = view.findViewById(R.id.btnDeposit)
        val btnWithdraw: Button = view.findViewById(R.id.btnWithdraw)
        val textSpentUSD: TextView = view.findViewById(R.id.textSpentUSD)
        val textSpentKRW: TextView = view.findViewById(R.id.textSpentKRW)
        val textRemainingUSD: TextView = view.findViewById(R.id.textRemainingUSD)
        val textRemainingKRW: TextView = view.findViewById(R.id.textRemainingKRW)
//        val recyclerViewRecentTransactions: RecyclerView = view.findViewById(R.id.recyclerViewRecentTransactions)

//        recyclerViewRecentTransactions.layoutManager = LinearLayoutManager(requireContext())
//        recyclerViewRecentTransactions.adapter = transactionAdapter

        btnDeposit.setOnClickListener {
            val amount = editTextAmount.text.toString().toIntOrNull()
            if (amount != null) {
                val currentDate = getCurrentDate()
                remainingUSD += amount
                remainingKRW += amount * exchangeRate
                transactionList.add("$currentDate - 입금: USD $amount")
//                transactionAdapter.notifyItemInserted(transactionList.size - 1)
//                recyclerViewRecentTransactions.scrollToPosition(transactionList.size - 1)
                updateUI(textSpentUSD, textSpentKRW, textRemainingUSD, textRemainingKRW)
            }
        }

        btnWithdraw.setOnClickListener {
            val amount = editTextAmount.text.toString().toIntOrNull()
            if (amount != null) {
                val currentDate = getCurrentDate()
                spentUSD += amount
                spentKRW += amount * exchangeRate
                remainingUSD -= amount
                remainingKRW -= amount * exchangeRate
                transactionList.add("$currentDate - 지불: USD $amount")
//                transactionAdapter.notifyItemInserted(transactionList.size - 1)
//                recyclerViewRecentTransactions.scrollToPosition(transactionList.size - 1)
                updateUI(textSpentUSD, textSpentKRW, textRemainingUSD, textRemainingKRW)
            }
        }

        return view
    }

    private fun updateUI(
        textSpentUSD: TextView,
        textSpentKRW: TextView,
        textRemainingUSD: TextView,
        textRemainingKRW: TextView
    ) {
        textSpentUSD.text = "USD $spentUSD"
        textSpentKRW.text = "KRW $spentKRW"
        textRemainingUSD.text = "USD $remainingUSD"
        textRemainingKRW.text = "KRW $remainingKRW"
    }

    private fun getCurrentDate(): String {
        val dateFormat = java.text.SimpleDateFormat("yyyy.MM.dd", java.util.Locale.getDefault())
        return dateFormat.format(java.util.Date())
    }
}
