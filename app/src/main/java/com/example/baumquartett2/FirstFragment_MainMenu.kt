package com.example.baumquartett2

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_mainmenu.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment_MainMenu : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mainmenu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(SpielEinstellungen.background == 0) SpielEinstellungen.background= (Math.random() * 10 + 1).toInt()
        when(SpielEinstellungen.background)
        {
            1 -> Glide.with(requireContext())
                .load(R.drawable.backgroundblue)
                .into(imageViewMainBackground)
            2 -> Glide.with(requireContext())
                .load(R.drawable.backgrounddizzy)
                .into(imageViewMainBackground)
            3 -> Glide.with(requireContext())
                .load(R.drawable.backgroundclassic)
                .into(imageViewMainBackground)
            4 -> Glide.with(requireContext())
                .load(R.drawable.backgroundwaterhome)
                .into(imageViewMainBackground)
            5 -> Glide.with(requireContext())
                .load(R.drawable.backgroundred)
                .into(imageViewMainBackground)
            6 -> Glide.with(requireContext())
                .load(R.drawable.backgroundmountain)
                .into(imageViewMainBackground)
            7 -> Glide.with(requireContext())
                .load(R.drawable.backgroundrainforest)
                .into(imageViewMainBackground)
            8 -> Glide.with(requireContext())
                .load(R.drawable.backgroundrainy)
                .into(imageViewMainBackground)
            9 -> Glide.with(requireContext())
                .load(R.drawable.backgroundwater)
                .into(imageViewMainBackground)
            10 -> Glide.with(requireContext())
                .load(R.drawable.trees)
                .into(imageViewMainBackground)
            11 -> Glide.with(requireContext())
                .load(R.drawable.backgroundmorning)
                .into(imageViewMainBackground)
        }


        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            d("TEST","test")
        }

        view.findViewById<Button>(R.id.button_first2).setOnClickListener {
            findNavController().navigate(R.id.thirdFragment)
            d("TEST","test")
        }

        view.findViewById<Button>(R.id.button_einstellungen).setOnClickListener {
            //startActivity(Intent(this,Einstellungen::class.java))

            findNavController().navigate(R.id.menu_to_einstellungen)
            d("TEST2","test2")
        }
    }
}
