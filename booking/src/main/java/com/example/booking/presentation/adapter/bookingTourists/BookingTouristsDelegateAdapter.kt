package com.example.booking.presentation.adapter.bookingTourists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.databinding.ExpandedItemBinding
import com.example.booking.domain.model.TouristInfo
import com.example.booking.presentation.adapter.util.OnAddItemClickListener
import com.example.util.adapter.DelegateAdapter
import com.example.util.adapter.DelegateAdapterItem
class BookingTouristsDelegateAdapter(
    private var onAddClickListener: OnAddItemClickListener,

    private val onCheckedAll: ((Boolean) -> Unit),
    private val onCollapsed: ((Boolean, Int) -> Unit),
    private val onsetFirstName: ((String, Int) -> Unit),
    private val onsetLastName: ((String, Int) -> Unit),
    private val onsetBirthDayDate: ((String, Int) -> Unit),
    private val onsetCitizenship: ((String, Int) -> Unit),
    private val onsetPassportNumber: ((String, Int) -> Unit),
    private val onsetPassportValidate: ((String, Int) -> Unit)
) :
    DelegateAdapter<BookingTouristsDelegate, BookingTouristsDelegateAdapter.BookingBuyerViewHolder>(
        BookingTouristsDelegate::class.java
    ) {

    private var touristsAdapter: ExpandableTouristListAdapter? = null
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        BookingBuyerViewHolder(
            ExpandedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun bindViewHolder(
        model: BookingTouristsDelegate,
        viewHolder: BookingBuyerViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>,
    ) {
        when (val payload = payloads.firstOrNull() as? BookingTouristsDelegate.ChangePayload) {

            is BookingTouristsDelegate.ChangePayload.TouristsChanged ->{
                viewHolder.bindCategoryChanged(payload.tourists)
            }

            is BookingTouristsDelegate.ChangePayload.TouristsCheckChanged ->{
                viewHolder.checkFields(model)
            }

            else -> viewHolder.bind(model)
        }
    }

    inner class BookingBuyerViewHolder(private val binding: ExpandedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BookingTouristsDelegate) {

            touristsAdapter = ExpandableTouristListAdapter(

                {
                    onCheckedAll(it)
                },
                { isCollapsed, pos ->
                    onCollapsed(isCollapsed, pos)
                },
                { firstName, pos ->
                    onsetFirstName(firstName, pos)
                },

                { lastName, pos ->
                    onsetLastName(lastName, pos)
                },

                { birthday, pos ->
                    onsetBirthDayDate(birthday, pos)
                },

                { citizenship, pos ->
                    onsetCitizenship(citizenship, pos)
                },

                { passportNumber, pos ->
                    onsetPassportNumber(passportNumber, pos)
                },
                { validate, pos ->
                    onsetPassportValidate(validate, pos)
                }
            )

            binding.touristsRecycler.adapter = touristsAdapter
            touristsAdapter?.submitList(model.tourists)

            binding.addTourist.setOnClickListener {
                onAddClickListener.onAddClick()
            }
        }

        fun bindCategoryChanged(tourists: ArrayList<TouristInfo>) {
               touristsAdapter?.submitList(tourists)
        }

        fun checkFields(
            model: BookingTouristsDelegate
        ) {
            val recyclerViewState = binding.touristsRecycler.layoutManager?.onSaveInstanceState()
            touristsAdapter?.submitList(model.tourists)
            binding.touristsRecycler.layoutManager?.onRestoreInstanceState(recyclerViewState)
        }
    }
}