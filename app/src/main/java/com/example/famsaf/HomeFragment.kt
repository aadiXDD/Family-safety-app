package com.example.famsaf

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {

    private val listContacts: ArrayList<ContactModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listMembers = listOf<MemberModel>(
            MemberModel("Aditya Raj", "16:40", "Agartala"),
            MemberModel("Nishu Kumari", "8:55", "Nawada, Bihar"),
            MemberModel("Nancy Heral", "14:09", "Chennai"),
            MemberModel("Arbind Kumar", "11:13", "Patna"),
            MemberModel("Madhuri Devi", "6:09", "Patna")
        )
        val adapter = MemberAdapter(listMembers)
        val recycler = requireView().findViewById<RecyclerView>(R.id.recycle_view)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

//        val listContacts = listOf<ContactModel>(
//            ContactModel("Rakesh Prasad", "123476733"),
//            ContactModel("Ashok Prasad", "4324353423"),
//            ContactModel("Sigma Singh", "9747313414"),
//            ContactModel("Rakesh Prasad", "123476733"),
//            ContactModel("Ashok Prasad", "4324353423"),
//            ContactModel("Sigma Singh", "9747313414"),
//            ContactModel("Rakesh Prasad", "123476733"),
//            ContactModel("Ashok Prasad", "4324353423"),
//            ContactModel("Sigma Singh", "9747313414"),
//            ContactModel("Rakesh Prasad", "123476733"),
//            ContactModel("Ashok Prasad", "4324353423"),
//            ContactModel("Sigma Singh", "9747313414")
//        )
        val inviteAdapter = InviteAdapter(fetchContacts())

        CoroutineScope(Dispatchers.IO).launch {
            listContacts.addAll(fetchContacts())

            withContext(Dispatchers.Main){
                inviteAdapter.notifyDataSetChanged()
            }
        }

        val invite_recycler = requireView().findViewById<RecyclerView>(R.id.recycler_invite)
        invite_recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        invite_recycler.adapter = inviteAdapter


    }

    @SuppressLint("Range")
    private fun fetchContacts(): ArrayList<ContactModel> {
        val cr = requireActivity().contentResolver
        val cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        val listContacts: ArrayList<ContactModel> = ArrayList()

        if(cursor != null && cursor.count > 0){
            while (cursor != null && cursor.moveToNext()){
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val hasPhoneNumber = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                if(hasPhoneNumber > 0){
                    val pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?", arrayOf(id), "")

                    if(pCur != null && pCur.count > 0){
                        while (pCur != null && pCur.moveToNext()){
                            val phoneNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            listContacts.add(ContactModel(name, phoneNumber))
                        }
                        pCur.close()
                    }

                }
            }

            if(cursor!= null){
                cursor.close()
            }

        }
        return listContacts
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}