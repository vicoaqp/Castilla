package com.turismo.castilla

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.turismo.castilla.databinding.FragmentFragDesHotelBinding
import com.turismo.castilla.databinding.FragmentFragLinkHotelBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragLinkHotel.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragLinkHotel : Fragment() {
    lateinit var layout: FragmentFragLinkHotelBinding
    private val Binding get() = layout
    var lhotelval:String?=""
    lateinit var link:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parentFragmentManager.setFragmentResultListener("fraglinkhotel",this,
            FragmentResultListener{ reStr: String, data: Bundle ->
                lhotelval= data.getString("hotellinkva")
                Log.i("resu",lhotelval.toString())
                //layout.tdes.text=ndistrito

                val db= Firebase.firestore
                db.collection("facebook")
                    .whereEqualTo("nameface",lhotelval)
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            layout.textprueba.text= document.data.get("url").toString()
                        }
                    }
            }
        )


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var datol:String?=""

        layout= FragmentFragLinkHotelBinding.inflate(inflater,container,false)

        layout.buttonprueba.setOnClickListener{
            datol=layout.textprueba.text.toString()
            val face= Intent(Intent.ACTION_VIEW, Uri.parse(datol))
            startActivity(face)

        }

        return layout.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragLinkHotel.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragLinkHotel().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}