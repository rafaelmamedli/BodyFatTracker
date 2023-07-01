package com.rafael.bodyfattracker.view

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rafael.bodyfattracker.R
import com.rafael.bodyfattracker.data.model.BodyFatModel
import com.rafael.bodyfattracker.databinding.FragmentCalculatorBinding
import com.rafael.bodyfattracker.data.util.goTo
import com.rafael.bodyfattracker.data.util.gone
import com.rafael.bodyfattracker.data.util.show
import com.rafael.bodyfattracker.data.util.toast
import com.rafael.bodyfattracker.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CalculatorFragment : Fragment() {

    private lateinit var binding: FragmentCalculatorBinding
    private val viewModel: ViewModel by viewModels()
    private var gender: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalculatorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chooseGender()

        binding.bodyFatHistoryButton.setOnClickListener {
            goTo(it, R.id.action_calculatorFragment_to_bodyFatHistory)
        }

    }

    override fun onResume() {
        super.onResume()
        calculateAndSave()
        calculateAndClear()

    }

    private fun chooseGender() {
        binding.toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnMale -> {
                        gender = true
                        binding.abdomenTextView.text = "Abdomen:"
                        binding.hipLayout.gone()
                        calculateAndSave()
                    }
                    R.id.btnFemale -> {
                        gender = false
                        binding.abdomenTextView.text = "Waist:"
                        binding.hipLayout.show()
                        calculateAndSave()
                    }
                }
            } else {
                if (toggleButtonGroup.checkedButtonId == View.NO_ID) {
                }
            }
        }
    }

    private fun calculateAndSave() {
        viewModel.result.observe(viewLifecycleOwner) { result ->
            val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            val formattedNumber = "${result.substring(0, 4)}%"
            binding.bodyFatResult.text = formattedNumber
            val bodyFat = binding.bodyFatResult.text

            binding.saveBtn.setOnClickListener {
                if (bodyFat.isNotEmpty() && formattedNumber > 0.3.toString() && formattedNumber.toString() != "NaN %") {
                    viewModel.insert(BodyFatModel(0, bodyFat.toString(), currentDate))
                }
            }
        }
    }

    private fun getCalculate(
        height: Editable?,
        weight: Editable?,
        neck: Editable?,
        abdomen: Editable?,
        gender: Boolean,
        hipValue: Editable?
    ) {
        viewModel.bodyFatProCalculator(weight, height, neck, abdomen, gender, hipValue)
    }

    private fun calculateAndClear() {
        val heightValue = binding.heightEditText.text
        val weightValue = binding.weightEditText.text
        val neckValue = binding.neckEditText.text
        val abdomenValue = binding.abdomenEditText.text
        val hipValue = binding.hipEditText.text
        binding.calculate.setOnClickListener {
            gender?.let { it1 ->
                getCalculate(
                    heightValue, weightValue, neckValue, abdomenValue,
                    it1, hipValue
                )
            } ?: toast("Choose Gender")

        }
        binding.resetBtn.setOnClickListener {
            heightValue.clear()
            weightValue.clear()
            neckValue.clear()
            abdomenValue.clear()
            hipValue.clear()
            binding.bodyFatResult.text = "0.00%"
        }


    }
}