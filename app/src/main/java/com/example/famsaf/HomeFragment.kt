package com.example.famsaf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {

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

        val listContacts = listOf<ContactModel>(
            ContactModel("Rakesh Prasad", "123476733"),
            ContactModel("Ashok Prasad", "4324353423"),
            ContactModel("Sigma Singh", "9747313414"),
            ContactModel("Rakesh Prasad", "123476733"),
            ContactModel("Ashok Prasad", "4324353423"),
            ContactModel("Sigma Singh", "9747313414"),
            ContactModel("Rakesh Prasad", "123476733"),
            ContactModel("Ashok Prasad", "4324353423"),
            ContactModel("Sigma Singh", "9747313414"),
            ContactModel("Rakesh Prasad", "123476733"),
            ContactModel("Ashok Prasad", "4324353423"),
            ContactModel("Sigma Singh", "9747313414")
        )
        val inviteAdapter = InviteAdapter(listContacts)
        val invite_recycler = requireView().findViewById<RecyclerView>(R.id.recycler_invite)
        invite_recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        invite_recycler.adapter = inviteAdapter
    }



    companion object {
        fun newInstance() = HomeFragment()
    }
}