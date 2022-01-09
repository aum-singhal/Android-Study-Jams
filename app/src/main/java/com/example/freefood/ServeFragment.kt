package com.example.freefood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ServeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ServeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_serve, container, false)

        val addBTN = v.findViewById<Button>(R.id.addBTN)
        val sname = v.findViewById<EditText>(R.id.sname)
        val sphone = v.findViewById<EditText>(R.id.sphone)
        val saddr = v.findViewById<EditText>(R.id.saddr)
        val spin = v.findViewById<EditText>(R.id.spin)
        val smenu = v.findViewById<EditText>(R.id.smenu)
        val sdate = v.findViewById<EditText>(R.id.sdate)
        val squantity = v.findViewById<EditText>(R.id.squantity)
        val auth = Firebase.auth
        val db = FirebaseDatabase.getInstance().getReference("Service")

        addBTN.setOnClickListener {
            var name = sname.text.toString()
            var phone = sphone.text.toString()
            var addr = saddr.text.toString()
            var pin = spin.text.toString()
            var menu = smenu.text.toString()
            var date = sdate.text.toString()
            var count = squantity.text.toString()

            var service = Service(name, phone, addr, date, pin, count, menu)

            val uid = auth.currentUser?.uid
            if(uid != null){
                if((name != "") && (phone != "") && (addr != "") && (pin != "") && (date != "") && (count != "") && (menu != "")){
                    db.child(uid).setValue(service).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(activity, "Service Updated Successfully!", Toast.LENGTH_SHORT).show()
                            sname.clearFocus()
                            sphone.clearFocus()
                            saddr.clearFocus()
                            spin.clearFocus()
                            smenu.clearFocus()
                            sdate.clearFocus()
                        }
                        else{
                            Toast.makeText(activity, "Failed to add the service", LENGTH_LONG).show()
                        }
                    }
                }
                else{
                    Toast.makeText(activity, "All feilds are mandatory!", Toast.LENGTH_LONG).show()
                }
            }

        }

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ServeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ServeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}