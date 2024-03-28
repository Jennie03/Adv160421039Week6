package com.ubaya.adv160421039week6.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.adv160421039week6.databinding.PhoneListItemBinding
import com.ubaya.adv160421039week6.model.Phone

class PhoneListAdapter(val phoneList:ArrayList<Phone>):RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder>()
{
    class PhoneViewHolder(var binding: PhoneListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val binding = PhoneListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return PhoneViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        with(holder.binding) {
            txtID.text = phoneList[position].id.toString()
            txtBrand.text = phoneList[position].brand
            txtName.text = phoneList[position].name
            val features = phoneList[position].features
            holder.binding.txtFeatures.text = "Features:\n"
            if (features != null) {
                for (feat in features){
                    txtFeatures.append("-${features}")
                }
            }
            val specs = phoneList[position].specifications
            if (specs != null){
                txtSpecs.append("Spesifications:\n" +
                        specs.RAM + "\n" +
                        specs.Storage  + "\n" +
                        specs.Battery)
            }
        }
    }

    fun updatePhoneList(newPhoneList: ArrayList<Phone>) {
        phoneList.clear()
        phoneList.addAll(newPhoneList)
        notifyDataSetChanged()
    }
}
