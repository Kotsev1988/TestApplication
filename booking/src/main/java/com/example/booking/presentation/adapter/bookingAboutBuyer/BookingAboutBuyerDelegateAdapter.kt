package com.example.booking.presentation.adapter.bookingAboutBuyer

import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.R
import com.example.booking.databinding.ItemBuyerBinding
import com.example.util.adapter.DelegateAdapter
import com.example.util.adapter.DelegateAdapterItem
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class BookingAboutBuyerDelegateAdapter(
    private val onPhoneCorrectFilled: ((String) -> Unit),
    private val onEmailCorrectFilled: ((String) -> Unit),
) : DelegateAdapter<BookingAboutBuyerDelegate, BookingAboutBuyerDelegateAdapter.BookingBuyerViewHolder>(
    BookingAboutBuyerDelegate::class.java
) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        BookingBuyerViewHolder(
            ItemBuyerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun bindViewHolder(
        model: BookingAboutBuyerDelegate,
        viewHolder: BookingBuyerViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>,
    ) {
        viewHolder.bind(model)
    }

    inner class BookingBuyerViewHolder(private val binding: ItemBuyerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: BookingAboutBuyerDelegate) {
            binding.phoneNumberText.setOnFocusChangeListener { _, b ->
                if (b) {
                    binding.phoneNumberText.hint = "+7(***)***_**_**"
                } else {
                    binding.phoneNumberText.hint = ""
                    if (binding.phoneNumberText.text?.length != 18) {
                        binding.phoneNumberOfBuyer.boxBackgroundColor = ContextCompat.getColor(
                            binding.phoneNumberOfBuyer.context,
                            R.color.error_red
                        )

                    } else {
                        binding.phoneNumberOfBuyer.boxBackgroundColor = ContextCompat.getColor(
                            binding.phoneNumberOfBuyer.context,
                            R.color.text_field_color
                        )
                    }
                }
            }

            binding.phoneNumberText.addTextChangedListener {

                if (it != null && it.length == 18) {
                    onPhoneCorrectFilled(binding.phoneNumberText.text.toString())
                } else {
                    onPhoneCorrectFilled("")
                }
            }

            binding.emailOfBuyerText.addTextChangedListener {
                if (it != null && Patterns.EMAIL_ADDRESS.matcher(binding.emailOfBuyerText.text.toString()).matches()) {
                    onEmailCorrectFilled(binding.emailOfBuyerText.text.toString())
                } else {
                    onEmailCorrectFilled("")
                }
            }

            binding.emailOfBuyerText.setOnFocusChangeListener { _, b ->

                if (b) {
                    binding.emailOfBuyerText.hint = "example@example.com"
                } else {
                    binding.emailOfBuyerText.hint = ""

                    if (!binding.emailOfBuyerText.text.isNullOrEmpty()) {
                        if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailOfBuyerText.text.toString()).matches()) {
                            binding.emailOfBuyer.boxBackgroundColor = ContextCompat
                                .getColor(binding.emailOfBuyer.context, R.color.error_red)
                            onEmailCorrectFilled("")
                        } else {
                            binding.emailOfBuyer.boxBackgroundColor = ContextCompat
                                .getColor(
                                    binding.emailOfBuyer.context,
                                    R.color.text_field_color
                                )
                            onEmailCorrectFilled(binding.emailOfBuyerText.text.toString())
                        }
                    }else {

                        if (binding.emailOfBuyerText.text.isNullOrEmpty() && !b) {
                            binding.emailOfBuyer.boxBackgroundColor = ContextCompat
                                .getColor(
                                    binding.emailOfBuyer.context,
                                    R.color.error_red
                                )
                            onEmailCorrectFilled("")
                        }
                    }
                }
            }

            val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
            val watcher: FormatWatcher = MaskFormatWatcher(mask)
            watcher.installOn(binding.phoneNumberText)

            if (model.checkFilledContacts) {

                if (binding.emailOfBuyerText.text.isNullOrEmpty()) {

                    binding.emailOfBuyer.boxBackgroundColor = ContextCompat
                        .getColor(binding.emailOfBuyer.context, R.color.error_red)
                } else {
                    binding.emailOfBuyer.boxBackgroundColor = ContextCompat
                        .getColor(
                            binding.emailOfBuyer.context,
                            R.color.text_field_color
                        )
                }

                if (binding.phoneNumberText.text.isNullOrEmpty()) {
                    binding.phoneNumberOfBuyer.boxBackgroundColor = ContextCompat.getColor(
                        binding.phoneNumberOfBuyer.context,
                        R.color.error_red
                    )

                } else {
                    binding.phoneNumberOfBuyer.boxBackgroundColor = ContextCompat.getColor(
                        binding.phoneNumberOfBuyer.context,
                        R.color.text_field_color
                    )
                }

            }
        }
    }
}
