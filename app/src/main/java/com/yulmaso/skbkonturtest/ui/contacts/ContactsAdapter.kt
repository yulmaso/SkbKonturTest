package com.yulmaso.skbkonturtest.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.yulmaso.skbkonturtest.data.model.Contact
import com.yulmaso.skbkonturtest.databinding.ItemContactBinding
import java.util.*
import kotlin.collections.ArrayList

class ContactsAdapter(
    private val contactsListener: ContactsListener
) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>(), Filterable {

    private val contacts: MutableList<Contact> = ArrayList()
    private val contactsFull: MutableList<Contact> = ArrayList()

    interface ContactsListener {
        fun onListItemClick(contact: Contact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder =
        ContactsViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            contactsListener
        )

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) =
        holder.bind(contacts[position])

    fun setItems(items: List<Contact>) {
        if (!items.isNullOrEmpty()) {
            contacts.apply {
                clear()
                addAll(items)
            }
            contactsFull.apply {
                clear()
                addAll(contacts)
            }
            notifyDataSetChanged()
        }
    }

    override fun getFilter() = object: Filter() {
        override fun performFiltering(searchInput: CharSequence?): FilterResults {
            val filteredList: MutableList<Contact> = ArrayList()

            if (searchInput == null || searchInput.isEmpty()) {
                filteredList.addAll(contactsFull)
            } else {
                val filterPattern = searchInput.toString().toLowerCase(Locale.ROOT).trim()
                for (item in contactsFull) {
                    val phoneWithoutSymbols = item.phone
                        .replace(" ", "")
                        .replace("(", "")
                        .replace(")", "")
                        .replace("-", "")
                    if (item.name.toLowerCase(Locale.ROOT).contains(filterPattern) || // Search in name
                        phoneWithoutSymbols.contains(filterPattern) || // Search in phone number
                        item.phone.contains(filterPattern)) { // Search in original phone number
                        filteredList.add(item)
                    }
                }
            }

            return FilterResults().apply { values = filteredList }
        }

        override fun publishResults(p0: CharSequence?, results: FilterResults?) {
            contacts.apply {
                clear()
                addAll(results?.values as List<Contact>)
            }
            notifyDataSetChanged()
        }
    }

    inner class ContactsViewHolder(
        private val binding: ItemContactBinding,
        private val contactsListener: ContactsListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(contact: Contact) {
            binding.apply {
                contactsName.text = contact.name
                contactsHeight.text = contact.height.toString()
                contactsPhone.text = contact.phone

                root.setOnClickListener(this@ContactsViewHolder)
            }
        }

        override fun onClick(p0: View?) {
            contactsListener.onListItemClick(contacts[layoutPosition])
        }
    }
}