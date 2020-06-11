package com.example.baumquartett2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_supertrumpfmenu.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment_SupertrumpfMenu : Fragment() {
    //TextView txt;
    //baumname_text.text = "abc"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supertrumpfmenu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        view.findViewById<Button>(R.id.button_second3).setOnClickListener {
            findNavController().navigate(R.id.fourthFragment_Supertrumpfgame)
            SpielEinstellungen.Spieler1 = "Spieler 1"
            SpielEinstellungen.Spieler2 = "Spieler 2"
        }

        view.findViewById<Button>(R.id.button_second2).setOnClickListener {
            SpielEinstellungen.vsAI = true ;
            SpielEinstellungen.Spieler1 = "Sie"
            SpielEinstellungen.Spieler2 = "KI"
            findNavController().navigate(R.id.fourthFragment_Supertrumpfgame)
        }

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.toMainMenu)
        }
        val baeumeVZ = BaeumeAnlegen()
        val Baumliste = baeumeVZ.BaeumeFuellen()

        SpielEinstellungen.AnzahlKarten = Baumliste.size/2
        SpielEinstellungen.Zeit = seekBarZeit.progress.toLong()
        seekBarAnz.max = SpielEinstellungen.AnzahlKarten
        seekBarAnz.progress = SpielEinstellungen.AnzahlKarten
        anzKarten.text = "Anzahl Karten :"+ SpielEinstellungen.AnzahlKarten
        seekBarZeit.isEnabled = SpielEinstellungen.ZeitAktiv
        kartenDurchschalten.isChecked = SpielEinstellungen.KartenDurchschalten
        SpielEinstellungen.vsAI = false

        if(SpielEinstellungen.ZeitAktiv==true)
        {
            switch2.isChecked=true
            seekBarZeit.isEnabled = true
        } else
        {
            switch2.isChecked=false
            seekBarZeit.isEnabled = false
        }

        Log.d("Gesamtsize Baumliste", ""+Baumliste.size)
        Log.d("Anz Karten ", ""+SpielEinstellungen.AnzahlKarten + " /2 " + SpielEinstellungen.AnzahlKarten/2 )

        kartenDurchschalten.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            // do something, the isChecked will be
            if(kartenDurchschalten.isChecked == true) SpielEinstellungen.KartenDurchschalten = true
            else SpielEinstellungen.KartenDurchschalten = false
        })

        view.findViewById<Switch>(R.id.switch2).setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            // do something, the isChecked will be
            if(switch2.isChecked == true)
            {
                seekBarZeit.isEnabled = true
                zeitAkt.text = "Zeit Aktuell : " +SpielEinstellungen.Zeit +" Min"
                SpielEinstellungen.ZeitAktiv = true
            }
            else
            {
                seekBarZeit.isEnabled = false
                zeitAkt.text = ""
                SpielEinstellungen.ZeitAktiv = false
            }
            // true if the switch is in the On position
        })

        //SeekBar change listener
        seekBarAnz.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBarAnz: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                anzKarten.text = "Anzahl Karten : $i"
                if(i!=0) SpielEinstellungen.AnzahlKarten = i else Toast.makeText(context,"Muss mind 1 sein!",Toast.LENGTH_SHORT).show()
            }

            override fun onStartTrackingTouch(seekBarAnz: SeekBar) {
                // Do something
                //Toast.makeText(context,"",Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBarAnz: SeekBar) {
                // Do something
                //Toast.makeText(context,"Kartenanzahl auf" +SpielEinstellungen.AnzahlKarten+ "geändert",Toast.LENGTH_SHORT).show()
            }
        })

        seekBarZeit.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBarZeit: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                zeitAkt.text = "Zeit Aktuell : " +i +" Min"
                if(i>0) SpielEinstellungen.Zeit  = i.toLong() else Toast.makeText(context,"Muss mind 1 sein!",Toast.LENGTH_SHORT).show()
            }

            override fun onStartTrackingTouch(seekBarZeit: SeekBar) {
                // Do something
                //Toast.makeText(context,"",Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(seekBarZeit: SeekBar) {
                // Do something
                //Toast.makeText(context,"Zeit auf" +zeit+ " min geändert",Toast.LENGTH_SHORT).show()
            }
        })
    }

}
