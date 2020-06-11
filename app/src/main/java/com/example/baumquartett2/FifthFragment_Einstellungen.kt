package com.example.baumquartett2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_einstellungen.*
import kotlinx.android.synthetic.main.fragment_supertrumpfmenu.*

class FifthFragment_Einstellungen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_einstellungen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            findNavController().navigate(R.id.einstellungen_to_menu)
        }

        if(SpielEinstellungen.Ton) tonaktiv.isChecked = true else tonaktiv.isChecked = false

        tonaktiv.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            // do something, the isChecked will be
            if(tonaktiv.isChecked == true) SpielEinstellungen.Ton = true
            else SpielEinstellungen.Ton = false
        })

        seekBarAILevel.progress = SpielEinstellungen.AILevel
        seekBarhintergrund.progress = SpielEinstellungen.background

        when(SpielEinstellungen.background)
        {
            0 -> hintergrund.text = "Hintergrund : Zufall"
            1 -> hintergrund.text = "Hintergrund : Blaue Blätter"
            2 -> hintergrund.text = "Hintergrund : Waldnifl"
            3 -> hintergrund.text = "Hintergrund : Schwarz Abstrakt"
            4 -> hintergrund.text = "Hintergrund : Wassertempel"
            5 -> hintergrund.text = "Hintergrund : Roter Schein"
            6 -> hintergrund.text = "Hintergrund : Bergwasserfall"
            7 -> hintergrund.text = "Hintergrund : Regenwald"
            8 -> hintergrund.text = "Hintergrund : Regenerische Blätter"
            9 -> hintergrund.text = "Hintergrund : Wasserlauf"
            10 -> hintergrund.text = "Hintergrund : Lichtung"
            11 -> hintergrund.text = "Hintergrund : Morgentau"
        }

        when(seekBarAILevel.progress)
        {
            0 -> aiLevel.text = "KI Schwierigkeit : Leicht"
            1 -> aiLevel.text = "KI Schwierigkeit : Normal"
            2 -> aiLevel.text = "KI Schwierigkeit : Schwer"
        }

        seekBarAILevel.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBarAILevel: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                when(i)
                {
                    0 -> aiLevel.text = "KI Schwierigkeit : Leicht"
                    1 -> aiLevel.text = "KI Schwierigkeit : Normal"
                    2 -> aiLevel.text = "KI Schwierigkeit : Schwer"
                }
                SpielEinstellungen.AILevel = i
            }
            override fun onStartTrackingTouch(seekBarAILevel: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBarAILevel: SeekBar) {
            }
        })

        seekBarhintergrund.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBarAILevel: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                when(i)
                {
                    0 -> hintergrund.text = "Hintergrund : Zufall"
                    1 -> hintergrund.text = "Hintergrund : Blaue Blätter"
                    2 -> hintergrund.text = "Hintergrund : Waldnifl"
                    3 -> hintergrund.text = "Hintergrund : Schwarz Abstrakt"
                    4 -> hintergrund.text = "Hintergrund : Wassertempel"
                    5 -> hintergrund.text = "Hintergrund : Roter Schein"
                    6 -> hintergrund.text = "Hintergrund : Bergwasserfall"
                    7 -> hintergrund.text = "Hintergrund : Regenwald"
                    8 -> hintergrund.text = "Hintergrund : Regenerische Blätter"
                    9 -> hintergrund.text = "Hintergrund : Wasserlauf"
                    10 -> hintergrund.text = "Hintergrund : Lichtung"
                    11 -> hintergrund.text = "Hintergrund : Morgentau"
                }
                SpielEinstellungen.background = i
            }
            override fun onStartTrackingTouch(seekBarAILevel: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBarAILevel: SeekBar) {
            }
        })
    }
}
