package com.example.booking.presentation.adapter.bookingTourists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.booking.R
import com.example.booking.databinding.ItemAboutTouristBinding
import com.example.booking.domain.model.TouristInfo
import com.example.booking.presentation.adapter.util.ChangePayloads
import com.example.booking.presentation.adapter.util.DiffCallback

class ExpandableTouristListAdapter(

    private val onCheckedAll: ((Boolean) -> Unit),
    private val onCollapsed: ((Boolean, Int) -> Unit),
    private val onsetFirstName: ((String, Int) -> Unit),
    private val onsetLastName: ((String, Int) -> Unit),
    private val onsetBirthDayDate: ((String, Int) -> Unit),
    private val onsetCitizenship: ((String, Int) -> Unit),
    private val onsetPassportNumber: ((String, Int) -> Unit),
    private val onsetPassportValidate: ((String, Int) -> Unit)
    ) : ListAdapter<TouristInfo, ExpandableTouristListAdapter.TouristListViewHolder>(DiffCallback()) {

    val countOfTourists: List<String> = listOf(
        "Первый турист",
        "Второй турист",
        "Третий турист",
        "Четвертый турист",
        "Пятый турист",
        "Шестой турист",
        "Седьмой турист"
    )


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ExpandableTouristListAdapter.TouristListViewHolder = TouristListViewHolder(
        ItemAboutTouristBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: ExpandableTouristListAdapter.TouristListViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: TouristListViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads[0] == ChangePayloads.IsAllFilled){
                holder.bindCheckingFields(getItem(position))
            }
        }
    }

    inner class TouristListViewHolder(private val binding: ItemAboutTouristBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(s: TouristInfo) {

            binding.header.text = countOfTourists[layoutPosition]

            if (s.checkFeilds == null) {
                binding.touristNameText.addTextChangedListener {
                    onsetFirstName(binding.touristNameText.text.toString(), layoutPosition)
                }

                binding.touristLastNameText.addTextChangedListener {
                    onsetLastName(binding.touristLastNameText.text.toString(), layoutPosition)
                }

                binding.touristDateBirthText.addTextChangedListener {
                    onsetBirthDayDate(binding.touristDateBirthText.text.toString(), layoutPosition)
                }

                binding.touristCitizenshipText.addTextChangedListener {
                    onsetCitizenship(binding.touristCitizenshipText.text.toString(), layoutPosition)
                }

                binding.touristPassportNumberText.addTextChangedListener {
                    onsetPassportNumber(
                        binding.touristPassportNumberText.text.toString(),
                        layoutPosition
                    )
                }

                binding.touristPassportDateOfValidityText.addTextChangedListener {
                    onsetPassportValidate(
                        binding.touristPassportDateOfValidityText.text.toString(),
                        layoutPosition
                    )
                }
            }


            binding.show.setOnClickListener {

                if (binding.linearLayout.visibility == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(binding.baseCardview, AutoTransition())
                    binding.linearLayout.visibility = View.GONE
                    binding.show.setImageResource(R.drawable.arrowopen)
                    onCollapsed(false, layoutPosition)
                } else {
                    TransitionManager.beginDelayedTransition(binding.baseCardview, AutoTransition())
                    binding.linearLayout.visibility = View.VISIBLE
                    binding.show.setImageResource(R.drawable.arrowclose)
                    onCollapsed(true, layoutPosition)
                }
            }
        }

        private fun checkNickNameField(){
            if (binding.touristNameText.text.isNullOrEmpty()) {
                binding.touristName.boxBackgroundColor = ContextCompat.getColor(
                    binding.touristLastName.context,
                    R.color.error_red
                )

            } else {
                binding.touristName.boxBackgroundColor = ContextCompat.getColor(
                    binding.touristLastName.context,
                    R.color.text_field_color
                )
            }
        }

        private fun checkLastNameField(){
            if (binding.touristLastNameText.text.isNullOrEmpty()) {
                binding.touristLastName.boxBackgroundColor = ContextCompat.getColor(
                    binding.touristLastName.context,
                    R.color.error_red
                )
            } else {
                binding.touristLastName.boxBackgroundColor = ContextCompat.getColor(
                    binding.touristLastName.context,
                    R.color.text_field_color
                )
            }
        }

        private fun checkBirthDayField(){
            if (binding.touristDateBirthText.text.isNullOrEmpty()) {
                binding.touristDateBirth.boxBackgroundColor = ContextCompat.getColor(
                    binding.touristDateBirth.context,
                    R.color.error_red
                )
            } else {
                binding.touristDateBirth.boxBackgroundColor = ContextCompat.getColor(
                    binding.touristDateBirth.context,
                    R.color.text_field_color
                )
            }
        }

        private fun checkCitizenshipField(){
            if (binding.touristCitizenshipText.text.isNullOrEmpty()) {
                binding.touristCitizenship.boxBackgroundColor = ContextCompat.getColor(
                    binding.touristCitizenship.context,
                    R.color.error_red
                )
            } else {
                binding.touristCitizenship.boxBackgroundColor = ContextCompat.getColor(
                    binding.touristCitizenship.context,
                    R.color.text_field_color
                )
            }
        }

        private fun checkTouristPassportField(){
            if (binding.touristPassportNumberText.text.isNullOrEmpty()) {
                binding.touristPassportNumber.boxBackgroundColor = ContextCompat.getColor(
                    binding.touristPassportNumber.context,
                    R.color.error_red
                )
            } else {
                binding.touristPassportNumber.boxBackgroundColor = ContextCompat.getColor(
                    binding.touristPassportNumber.context,
                    R.color.text_field_color
                )
            }
        }

        private fun checkTouristPassportDateOfValidityField(){
            if (binding.touristPassportDateOfValidityText.text.isNullOrEmpty()) {
                binding.touristPassportDateOfValidity.boxBackgroundColor =
                    ContextCompat.getColor(
                        binding.touristPassportDateOfValidity.context,
                        R.color.error_red
                    )

            } else {
                binding.touristPassportDateOfValidity.boxBackgroundColor =
                    ContextCompat.getColor(
                        binding.touristPassportDateOfValidity.context,
                        R.color.text_field_color
                    )
            }
        }

        private fun checkCollapsed(isCollapse: Boolean){
            if (isCollapse) {
                TransitionManager.beginDelayedTransition(binding.baseCardview, AutoTransition())
                binding.linearLayout.visibility = View.VISIBLE
                binding.show.setImageResource(R.drawable.arrowclose)
            }
        }

        fun bindCheckingFields(s: TouristInfo) {

            if (s.checkFeilds == false) {
                binding.touristNameText.addTextChangedListener {
                    checkNickNameField()
                }

                binding.touristLastNameText.addTextChangedListener {
                    checkLastNameField()
                }

                binding.touristDateBirthText.addTextChangedListener {
                    checkBirthDayField()
                }

                binding.touristCitizenshipText.addTextChangedListener {
                    checkCitizenshipField()
                }

                binding.touristPassportNumberText.addTextChangedListener {
                    checkTouristPassportField()
                }

                binding.touristPassportDateOfValidityText.addTextChangedListener {
                    checkTouristPassportDateOfValidityField()
                }

                checkCollapsed(s.collapsed)

                if (binding.touristNameText.text.isNullOrEmpty()) {
                    binding.touristName.boxBackgroundColor = ContextCompat.getColor(
                        binding.touristLastName.context,
                        R.color.error_red
                    )

                } else {

                    binding.touristNameText.setText(s.first_name)

                    binding.touristName.boxBackgroundColor = ContextCompat.getColor(
                        binding.touristLastName.context,
                        R.color.text_field_color
                    )

                }
                onsetFirstName(binding.touristNameText.text.toString(), layoutPosition)

                if (binding.touristLastNameText.text.isNullOrEmpty()) {
                    binding.touristLastName.boxBackgroundColor = ContextCompat.getColor(
                        binding.touristLastName.context,
                        R.color.error_red
                    )

                } else {
                    binding.touristLastName.boxBackgroundColor = ContextCompat.getColor(
                        binding.touristLastName.context,
                        R.color.text_field_color
                    )
                }
                onsetLastName(binding.touristLastNameText.text.toString(), layoutPosition)

                if (binding.touristDateBirthText.text.isNullOrEmpty()) {
                    binding.touristDateBirth.boxBackgroundColor = ContextCompat.getColor(
                        binding.touristDateBirth.context,
                        R.color.error_red
                    )

                } else {
                    binding.touristDateBirth.boxBackgroundColor = ContextCompat.getColor(
                        binding.touristDateBirth.context,
                        R.color.text_field_color
                    )
                }
                onsetBirthDayDate(binding.touristDateBirthText.text.toString(), layoutPosition)

                if (binding.touristCitizenshipText.text.isNullOrEmpty()) {
                    binding.touristCitizenship.boxBackgroundColor = ContextCompat.getColor(
                        binding.touristCitizenship.context,
                        R.color.error_red
                    )

                } else {
                    binding.touristCitizenship.boxBackgroundColor = ContextCompat.getColor(
                        binding.touristCitizenship.context,
                        R.color.text_field_color
                    )
                }
                onsetCitizenship(binding.touristCitizenshipText.text.toString(), layoutPosition)

                if (binding.touristPassportNumberText.text.isNullOrEmpty()) {
                    binding.touristPassportNumber.boxBackgroundColor = ContextCompat.getColor(
                        binding.touristPassportNumber.context,
                        R.color.error_red
                    )

                } else {
                    binding.touristPassportNumber.boxBackgroundColor = ContextCompat.getColor(
                        binding.touristPassportNumber.context,
                        R.color.text_field_color
                    )
                }
                onsetPassportNumber(
                    binding.touristPassportNumberText.text.toString(),
                    layoutPosition
                )

                if (binding.touristPassportDateOfValidityText.text.isNullOrEmpty()) {
                    binding.touristPassportDateOfValidity.boxBackgroundColor =
                        ContextCompat.getColor(
                            binding.touristPassportDateOfValidity.context,
                            R.color.error_red
                        )

                } else {
                    binding.touristPassportDateOfValidity.boxBackgroundColor =
                        ContextCompat.getColor(
                            binding.touristPassportDateOfValidity.context,
                            R.color.text_field_color
                        )

                }
                onsetPassportValidate(
                    binding.touristPassportDateOfValidityText.text.toString(),
                    layoutPosition
                )
            }

            if (s.checkFeilds == true) {
                checkCollapsed(s.collapsed)
                checkNickNameField()
                checkLastNameField()
                checkBirthDayField()
                checkCitizenshipField()
                checkTouristPassportField()
                checkTouristPassportDateOfValidityField()
                onCheckedAll(true)
            }
        }
    }
}


