package com.example.baumquartett2

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment_AlleAnsehen : Fragment() {
    //TextView txt;
    //baumname_text.text = "abc"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alleansehen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var i=0

        val baeumeVZ = BaeumeAnlegen()

        val Baumliste = baeumeVZ.BaeumeFuellen()
        //BaeumeAnlegen.baeumeUebergeben();

        //for (i in Baumliste.indices) {
        fun karteZeichnen(i: Int)
        {
            var tmpBaum = Baumliste[i]
            println(tmpBaum.name)

            if(tmpBaum.familie=="Laubbaum") {
                d("Wotangle","Tree 2 zeichnen")
                view.findViewById<ImageView>(R.id.baumart).setImageResource(R.drawable.tree_laub2)
            } else view.findViewById<ImageView>(R.id.baumart).setImageResource(R.drawable.tree_nadel)

            view.findViewById<TextView>(R.id.baumname_text).setText(tmpBaum.name)
            view.findViewById<TextView>(R.id.baumgattung_text).setText(tmpBaum.gattung)
            view.findViewById<TextView>(R.id.blattart_text).setText(tmpBaum.blatt)
            d("Wotangle", "Kartennr : "+tmpBaum.kartenNr)
            view.findViewById<TextView>(R.id.kartennr).setText(tmpBaum.kartenNr)
            view.findViewById<TextView>(R.id.beschreibungView).setText(tmpBaum.beschreibung)

            //Stats
            var HW : String=""
            var ST : String=""
            var GF : String=""
            var HH : String=""

            when(tmpBaum.holzwert)
            {
                    0 ->
                    {
                        HW = "Holzwert: Gering"
                        view.findViewById<TextView>(R.id.stat1).setBackgroundResource(R.drawable.bckgr_teal)
                    } //0-500€ pro m3
                    1 ->
                    {
                        HW = "Holzwert: Mittel"
                        view.findViewById<TextView>(R.id.stat1).setBackgroundResource(R.drawable.bckgr_ligrn)
                    }//500-1000€ pro m3
                    2 ->
                    {
                        HW = "Holzwert: Hoch"
                        view.findViewById<TextView>(R.id.stat1).setBackgroundResource(R.drawable.bckgr_bl)
                    }//1000-2000€ pro m3
                    3 ->
                    {
                        HW = "Holzwert: Edel"
                        view.findViewById<TextView>(R.id.stat1).setBackgroundResource(R.drawable.bckgr_pr2)
                    }//2000€+ pro m3
                }
            when(tmpBaum.seltenheit)
            {
                0 ->
                {
                    ST = "Seltenheit: Häufig"
                    view.findViewById<TextView>(R.id.stat3).setBackgroundResource(R.drawable.bckgr_teal)
                }
                1 ->
                {
                    ST = "Seltenheit: Öfters"
                    view.findViewById<TextView>(R.id.stat3).setBackgroundResource(R.drawable.bckgr_ligrn)
                }
                2 ->
                {
                    ST = "Seltenheit: Selten"
                    view.findViewById<TextView>(R.id.stat3).setBackgroundResource(R.drawable.bckgr_bl)
                }
                3 ->
                {
                    ST = "Seltenheit: Rar"
                    view.findViewById<TextView>(R.id.stat3).setBackgroundResource(R.drawable.bckgr_pr2)
                }
            }

            when(tmpBaum.giftigkeit)
            {
                0 -> {
                    GF = "Giftigkeit: Keine"
                    view.findViewById<TextView>(R.id.stat4).setBackgroundResource(R.drawable.bckgr_teal)
                }
                1 ->{
                    GF = "Giftigkeit: Gering"
                    view.findViewById<TextView>(R.id.stat4).setBackgroundResource(R.drawable.bckgr_ligrn)
                }
                2 -> {
                    GF = "Giftigkeit: Mittel"
                    view.findViewById<TextView>(R.id.stat4).setBackgroundResource(R.drawable.bckgr_bl)
                }
                3 -> {
                    GF = "Giftigkeit: Stark"
                    view.findViewById<TextView>(R.id.stat4).setBackgroundResource(R.drawable.bckgr_pr2)
                }
            }

            when(tmpBaum.maxWuchshoehe)
            {
                0 -> {
                    HH = "Größe: Klein"
                    view.findViewById<TextView>(R.id.stat2).setBackgroundResource(R.drawable.bckgr_teal)
                }
                1 ->{
                    HH = "Größe: Mittel"
                    view.findViewById<TextView>(R.id.stat2).setBackgroundResource(R.drawable.bckgr_ligrn)
                }
                2 -> {
                    HH = "Größe: Groß"
                    view.findViewById<TextView>(R.id.stat2).setBackgroundResource(R.drawable.bckgr_bl)
                }
                3 -> {
                    HH = "Größe: Riesig"
                    view.findViewById<TextView>(R.id.stat2).setBackgroundResource(R.drawable.bckgr_pr2)
                }
            }

            val gesamtwert = ((tmpBaum.maxWuchshoehe + tmpBaum.holzwert + tmpBaum.giftigkeit + tmpBaum.seltenheit )*10/4)
            if(gesamtwert<10) {
                view.findViewById<TextView>(R.id.gesamtwert).setBackgroundResource(R.drawable.bckgr_teal)
            }
            if(gesamtwert>=10 && gesamtwert<15) {
                view.findViewById<TextView>(R.id.gesamtwert).setBackgroundResource(R.drawable.bckgr_ligrn)
            }
            if(gesamtwert>=15 && gesamtwert<20) {
                view.findViewById<TextView>(R.id.gesamtwert).setBackgroundResource(R.drawable.bckgr_bl)
            }
            if(gesamtwert>=20) {
                view.findViewById<TextView>(R.id.gesamtwert).setBackgroundResource(R.drawable.bckgr_pr2)
            }

            view.findViewById<TextView>(R.id.stat1).setText(HW)
            view.findViewById<TextView>(R.id.stat2).setText(HH)
            view.findViewById<TextView>(R.id.stat3).setText(ST)
            view.findViewById<TextView>(R.id.stat4).setText(GF)
            view.findViewById<TextView>(R.id.gesamtwert).setText(Integer.toString(gesamtwert))
            view.findViewById<TextView>(R.id.gesamtwert).bringToFront()


            if(tmpBaum.kartenNr == "A1") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.bergahorn)
            if(tmpBaum.kartenNr == "A2") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.rotbuche)
            if(tmpBaum.kartenNr == "A3") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.apfel)
            if(tmpBaum.kartenNr == "A4") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.pappel)

            if(tmpBaum.kartenNr == "B1") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.kastanie)
            if(tmpBaum.kartenNr == "B2") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.esche)
            if(tmpBaum.kartenNr == "B3") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.goldregen)
            if(tmpBaum.kartenNr == "B4") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.robinie)

            if (tmpBaum.kartenNr == "C1") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.zypresse)
            if (tmpBaum.kartenNr == "C2") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.schwerinskiefer)
            if (tmpBaum.kartenNr == "C3") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.tamariske)
            if (tmpBaum.kartenNr == "C4") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.kasuarine)

            if(tmpBaum.kartenNr == "D1") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.eibe3)
            if(tmpBaum.kartenNr == "D2") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.fichte)
            if(tmpBaum.kartenNr == "D3") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.douglasie)
            if(tmpBaum.kartenNr == "D4") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.andentanne)

            if(tmpBaum.kartenNr == "E1") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.mammutbaum)
            if(tmpBaum.kartenNr == "E2") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.lebensbaum)

            if(tmpBaum.kartenNr == "F1") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.kiefer)
            if(tmpBaum.kartenNr == "F2") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.laerche)


        }

        karteZeichnen(0);

        view.findViewById<ImageButton>(R.id.nextcard_btn).setOnClickListener {
            d("Wotangle","Pressed Nextcard with current index of " + i)
            if(i<  (Baumliste.size - 1))
            {
                i++
                karteZeichnen(i)
                d("Wotangle","Index changed to " +i)
            }
        }

        view.findViewById<ImageButton>(R.id.prevcard_btn).setOnClickListener {
            d("CON","Pressed Prevcard with current index of " +i)
            if(i>=1)
            {
                i--
                karteZeichnen(i)
                d("Wotangle","Index changed to " +i)
            }
        }

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}
