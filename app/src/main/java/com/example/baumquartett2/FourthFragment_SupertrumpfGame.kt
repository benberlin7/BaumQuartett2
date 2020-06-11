package com.example.baumquartett2

import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_supertrumpf_game.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FourthFragment_SupertrumpfGame : Fragment() {
    //TextView txt;
    //baumname_text.text = "abc"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supertrumpf_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var Spieler1i=0
        var Spieler2i=0
        var schonausgeteilt=0
        var aktSpieler = 1;
        var sieg = 0;
        var unentschiedenCounter = 0;
        var initSpielerwechsel = 0;
        val playerSwitchDialog = AlertDialog.Builder(context)
        //playerSwitchDialog.setCanceledOnTouchOutside(false);
        playerSwitchDialog.setCancelable(false);
        val baeumeVZ = BaeumeAnlegen()
        val Baumliste = baeumeVZ.BaeumeFuellen()
        val spielerBlatt1: MutableList<Baum> = ArrayList()
        val spielerBlatt2: MutableList<Baum> = ArrayList()
        var tmpBaum : Baum = Baumliste.get(0)
        var mediaPlayer: MediaPlayer? = null
        var currVolume = 0;

        //MUSIK STARTEN
        mediaPlayer = MediaPlayer.create(context,R.raw.forest)
        if(SpielEinstellungen.music)
        {
            val log1 =
                (Math.log((SpielEinstellungen.maxVolume).toDouble() - currVolume.toDouble()) / Math.log(
                    SpielEinstellungen.maxVolume.toDouble()
                )).toFloat()
            mediaPlayer?.setVolume(log1, log1) //set volume takes two paramater


            mediaPlayer?.start()
            //mediaPlayer.setLooping(true)
        }

        //HINTERGRUND EINSTELLEN
        if(SpielEinstellungen.background == 0) SpielEinstellungen.background= (Math.random() * 10 + 1).toInt()
        when(SpielEinstellungen.background)
        {
            1 -> Glide.with(requireContext())
                .load(R.drawable.backgroundblue)
                .into(imageViewMainBackground2)
            2 -> Glide.with(requireContext())
                .load(R.drawable.backgrounddizzy)
                .into(imageViewMainBackground2)
            3 -> Glide.with(requireContext())
                .load(R.drawable.backgroundclassic)
                .into(imageViewMainBackground2)
            4 -> Glide.with(requireContext())
                .load(R.drawable.backgroundwaterhome)
                .into(imageViewMainBackground2)
            5 -> Glide.with(requireContext())
                .load(R.drawable.backgroundred)
                .into(imageViewMainBackground2)
            6 -> Glide.with(requireContext())
                .load(R.drawable.backgroundmountain)
                .into(imageViewMainBackground2)
            7 -> Glide.with(requireContext())
                .load(R.drawable.backgroundrainforest)
                .into(imageViewMainBackground2)
            8 -> Glide.with(requireContext())
                .load(R.drawable.backgroundrainy)
                .into(imageViewMainBackground2)
            9 -> Glide.with(requireContext())
                .load(R.drawable.backgroundwater)
                .into(imageViewMainBackground2)
            10 -> Glide.with(requireContext())
                .load(R.drawable.trees)
                .into(imageViewMainBackground2)
            11 -> Glide.with(requireContext())
                .load(R.drawable.backgroundmorning)
                .into(imageViewMainBackground2)
        }


        val timer = object: CountDownTimer(SpielEinstellungen.Zeit *60 *1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                try {
                    timeLeft.text = "" +millisUntilFinished/1000
                } catch (e: NumberFormatException)
                { d("WOTANLGE","-------TIMER ERROR-----") }
            }

            override fun onFinish() {

                if(spielerBlatt1.size>spielerBlatt2.size)
                    {
                        mediaPlayer = MediaPlayer.create(context,R.raw.won)
                        if(SpielEinstellungen.Ton) mediaPlayer?.start()
                        playerSwitchDialog.setTitle("Spielende")
                        playerSwitchDialog.setMessage("Zeit abgelaufen. Spieler 1 hat gewonnen !")
                        playerSwitchDialog.setNeutralButton("OK") { _, _ ->
                            button_second.performClick()
                        }

                    }
                    if(spielerBlatt1.size<spielerBlatt2.size) {
                        if(!SpielEinstellungen.vsAI)
                        {
                            mediaPlayer = MediaPlayer.create(context,R.raw.won)
                            if(SpielEinstellungen.Ton) mediaPlayer?.start()
                        } else
                        {
                            mediaPlayer = MediaPlayer.create(context,R.raw.low)
                            if(SpielEinstellungen.Ton) mediaPlayer?.start()
                        }
                        playerSwitchDialog.setTitle("Spielende")
                        playerSwitchDialog.setMessage("Zeit abgelaufen. Spieler 2 hat gewonnen !")
                        playerSwitchDialog.setNeutralButton("OK") { _, _ ->
                            button_second.performClick()
                        }
                    }
                        if(spielerBlatt1.size==spielerBlatt2.size) {
                            playerSwitchDialog.setTitle("Spielende")
                            playerSwitchDialog.setMessage("Zeit abgelaufen. Unentschieden !")
                            playerSwitchDialog.setNeutralButton("OK") { _, _ ->
                                button_second.performClick()
                            }
                        }
                        val dialog: AlertDialog = playerSwitchDialog.create()
                        dialog.show()
                        sieg=1
                    }
        }
        if(SpielEinstellungen.ZeitAktiv)
        {
        timer.start()
        } else
        {
            timeLeft.text = "∞"
        }


        //Spielerblätter Karten ziehen
        for (i in 0 until SpielEinstellungen.AnzahlKarten) {

            do
            {
                schonausgeteilt=0
                var zufallsKarte = (Math.random() * Baumliste.size).toInt()

                tmpBaum = Baumliste[zufallsKarte]
                for(i in 0 until (spielerBlatt1.size ))
                {
                    if(tmpBaum.kartenNr == spielerBlatt1.get(i).kartenNr) schonausgeteilt=1
                }
                for(i in 0 until (spielerBlatt2.size ))
                {
                    if(tmpBaum.kartenNr == spielerBlatt2.get(i).kartenNr) schonausgeteilt=1
                }
            }while(schonausgeteilt==1);

            spielerBlatt1.add(tmpBaum)

            do
            {
                schonausgeteilt=0
                var zufallsKarte = (Math.random() * Baumliste.size).toInt()
                tmpBaum = Baumliste.get(zufallsKarte)
                for(i in 0 until (spielerBlatt1.size))
                {
                    if(tmpBaum.kartenNr == spielerBlatt1.get(i).kartenNr) schonausgeteilt=1
                }
                for(i in 0 until (spielerBlatt2.size))
                {
                    if(tmpBaum.kartenNr == spielerBlatt2.get(i).kartenNr) schonausgeteilt=1
                }
            } while(schonausgeteilt==1)
            spielerBlatt2.add(tmpBaum)
        }

        fun karteZeichnen(tmpBaum: Baum)
        {

            //SIEGBEDINGUNGEN
               if(spielerBlatt1.size==0 || spielerBlatt2.size==0)
            {
                if(spielerBlatt1.size==0)
                {
                    if(SpielEinstellungen.vsAI==false) mediaPlayer = MediaPlayer.create(context,R.raw.won)
                    else mediaPlayer = MediaPlayer.create(context,R.raw.low)
                    if(SpielEinstellungen.Ton) mediaPlayer?.start()
                    playerSwitchDialog.setTitle("Spielende")
                    playerSwitchDialog.setMessage("Spieler 2 hat gewonnen !")
                    playerSwitchDialog.setNeutralButton("OK") { _, _ ->
                        button_second.performClick()
                    }
                }
                else
                {
                    mediaPlayer = MediaPlayer.create(context,R.raw.won)
                    if(SpielEinstellungen.Ton) mediaPlayer?.start()
                    playerSwitchDialog.setTitle("Spielende")
                    playerSwitchDialog.setMessage("Spieler 1 hat gewonnen !")
                    playerSwitchDialog.setNeutralButton("OK") { _, _ ->
                        button_second.performClick()
                    }
                }
                val dialog: AlertDialog = playerSwitchDialog.create()
                dialog.show()
                sieg=1;
            }

            println(tmpBaum.name)
            player1cards.text = "" + spielerBlatt1.size
            player2cards.text = "" + spielerBlatt2.size

            if(SpielEinstellungen.vsAI && aktSpieler == 2 && sieg==0)
            {
                kartenRuecken.setImageResource(R.drawable.card_back)
                kartenbild.isVisible = false
                baumart.isVisible = false
                gesamtwert.isVisible = false
                blattart_text.isVisible = false
                kartennr.isVisible = false
                baumname_text.isVisible = false
                baumgattung_text.isVisible = false
                beschreibungView.isVisible = false
                stat1.isVisible = false
                stat2.isVisible = false
                stat3.isVisible = false
                stat4.isVisible = false

                var hoechste = 0
                var stelle = 0

                //Leichte KI
                if(SpielEinstellungen.AILevel==0)
                {
                    stelle = (Math.random() * 4).toInt()
                }
                //Normale KI
                if(SpielEinstellungen.AILevel==1)
                {
                    hoechste = tmpBaum.holzwert
                    if (tmpBaum.maxWuchshoehe > tmpBaum.holzwert) {
                        hoechste = tmpBaum.maxWuchshoehe; stelle = 1;
                    }
                    if (tmpBaum.seltenheit > hoechste) {
                        hoechste = tmpBaum.seltenheit; stelle = 2;
                    }
                    if (tmpBaum.giftigkeit > hoechste) {
                        hoechste = tmpBaum.giftigkeit; stelle = 3;
                    }
                    if (unentschiedenCounter > 3) stelle = (Math.random() * 4).toInt()
                }
                //Schwere KI
                if(SpielEinstellungen.AILevel==2)
                {
                    hoechste = tmpBaum.giftigkeit
                    stelle=3

                    if (tmpBaum.seltenheit > tmpBaum.giftigkeit) {
                        hoechste = tmpBaum.seltenheit; stelle = 2;
                    }
                    if (tmpBaum.maxWuchshoehe > hoechste) {
                        hoechste = tmpBaum.maxWuchshoehe; stelle = 1;
                    }
                    if (tmpBaum.holzwert > hoechste) {
                        hoechste = tmpBaum.holzwert; stelle = 0;
                    }
                    if (unentschiedenCounter > 10) stelle = (Math.random() * 4).toInt()
                }
                when(stelle)
                {
                    0 -> holzwertspielen_button.performClick()
                    1 -> groeßespielen_button.performClick()
                    2 -> seltenheitspielen_button2.performClick()
                    3 -> giftspielen_button.performClick()
                }
            } else {
                kartenRuecken.setImageResource(R.drawable.card_back_blank)
                kartenbild.isVisible = true
                baumart.isVisible = true
                gesamtwert.isVisible = true
                blattart_text.isVisible = true
                kartennr.isVisible = true
                baumname_text.isVisible = true
                baumgattung_text.isVisible = true
                beschreibungView.isVisible = true
                stat1.isVisible = true
                stat2.isVisible = true
                stat3.isVisible = true
                stat4.isVisible = true

                //BEGINN ZEICHNUNG


                if (tmpBaum.familie == "Laubbaum") {
                    view.findViewById<ImageView>(R.id.baumart)
                        .setImageResource(R.drawable.tree_laub2)
                } else view.findViewById<ImageView>(R.id.baumart)
                    .setImageResource(R.drawable.tree_nadel)

                view.findViewById<TextView>(R.id.baumname_text).setText(tmpBaum.name)
                view.findViewById<TextView>(R.id.baumgattung_text).setText(tmpBaum.gattung)
                view.findViewById<TextView>(R.id.blattart_text).setText(tmpBaum.blatt)
                view.findViewById<TextView>(R.id.kartennr).setText(tmpBaum.kartenNr)
                view.findViewById<TextView>(R.id.beschreibungView).setText(tmpBaum.beschreibung)

                //Stats
                var HW: String = ""
                var ST: String = ""
                var GF: String = ""
                var HH: String = ""

                when (tmpBaum.holzwert) {
                    0 -> {
                        HW = "Holzwert: Gering"
                        view.findViewById<TextView>(R.id.stat1)
                            .setBackgroundResource(R.drawable.bckgr_teal)
                    } //0-500€ pro m3
                    1 -> {
                        HW = "Holzwert: Mittel"
                        view.findViewById<TextView>(R.id.stat1)
                            .setBackgroundResource(R.drawable.bckgr_ligrn)
                    }//500-1000€ pro m3
                    2 -> {
                        HW = "Holzwert: Hoch"
                        view.findViewById<TextView>(R.id.stat1)
                            .setBackgroundResource(R.drawable.bckgr_bl)
                    }//1000-2000€ pro m3
                    3 -> {
                        HW = "Holzwert: Edel"
                        view.findViewById<TextView>(R.id.stat1)
                            .setBackgroundResource(R.drawable.bckgr_pr2)
                    }//2000€+ pro m3
                }
                when (tmpBaum.seltenheit) {
                    0 -> {
                        ST = "Seltenheit: Häufig"
                        view.findViewById<TextView>(R.id.stat3)
                            .setBackgroundResource(R.drawable.bckgr_teal)
                    }
                    1 -> {
                        ST = "Seltenheit: Öfters"
                        view.findViewById<TextView>(R.id.stat3)
                            .setBackgroundResource(R.drawable.bckgr_ligrn)
                    }
                    2 -> {
                        ST = "Seltenheit: Selten"
                        view.findViewById<TextView>(R.id.stat3)
                            .setBackgroundResource(R.drawable.bckgr_bl)
                    }
                    3 -> {
                        ST = "Seltenheit: Rar"
                        view.findViewById<TextView>(R.id.stat3)
                            .setBackgroundResource(R.drawable.bckgr_pr2)
                    }
                }

                when (tmpBaum.giftigkeit) {
                    0 -> {
                        GF = "Giftigkeit: Keine"
                        view.findViewById<TextView>(R.id.stat4)
                            .setBackgroundResource(R.drawable.bckgr_teal)
                    }
                    1 -> {
                        GF = "Giftigkeit: Gering"
                        view.findViewById<TextView>(R.id.stat4)
                            .setBackgroundResource(R.drawable.bckgr_ligrn)
                    }
                    2 -> {
                        GF = "Giftigkeit: Mittel"
                        view.findViewById<TextView>(R.id.stat4)
                            .setBackgroundResource(R.drawable.bckgr_bl)
                    }
                    3 -> {
                        GF = "Giftigkeit: Stark"
                        view.findViewById<TextView>(R.id.stat4)
                            .setBackgroundResource(R.drawable.bckgr_pr2)
                    }
                }

                when (tmpBaum.maxWuchshoehe) {
                    0 -> {
                        HH = "Größe: Klein"
                        view.findViewById<TextView>(R.id.stat2)
                            .setBackgroundResource(R.drawable.bckgr_teal)
                    }
                    1 -> {
                        HH = "Größe: Mittel"
                        view.findViewById<TextView>(R.id.stat2)
                            .setBackgroundResource(R.drawable.bckgr_ligrn)
                    }
                    2 -> {
                        HH = "Größe: Groß"
                        view.findViewById<TextView>(R.id.stat2)
                            .setBackgroundResource(R.drawable.bckgr_bl)
                    }
                    3 -> {
                        HH = "Größe: Riesig"
                        view.findViewById<TextView>(R.id.stat2)
                            .setBackgroundResource(R.drawable.bckgr_pr2)
                    }
                }

                val gesamtwert =
                    ((tmpBaum.maxWuchshoehe + tmpBaum.holzwert + tmpBaum.giftigkeit + tmpBaum.seltenheit) * 10 / 4)
                if (gesamtwert < 10) {
                    view.findViewById<TextView>(R.id.gesamtwert)
                        .setBackgroundResource(R.drawable.bckgr_teal)
                }
                if (gesamtwert >= 10 && gesamtwert < 15) {
                    view.findViewById<TextView>(R.id.gesamtwert)
                        .setBackgroundResource(R.drawable.bckgr_ligrn)
                }
                if (gesamtwert >= 15 && gesamtwert < 20) {
                    view.findViewById<TextView>(R.id.gesamtwert)
                        .setBackgroundResource(R.drawable.bckgr_bl)
                }
                if (gesamtwert >= 20) {
                    view.findViewById<TextView>(R.id.gesamtwert)
                        .setBackgroundResource(R.drawable.bckgr_pr2)
                }

                view.findViewById<TextView>(R.id.stat1).setText(HW)
                view.findViewById<TextView>(R.id.stat2).setText(HH)
                view.findViewById<TextView>(R.id.stat3).setText(ST)
                view.findViewById<TextView>(R.id.stat4).setText(GF)
                view.findViewById<TextView>(R.id.gesamtwert).setText(Integer.toString(gesamtwert))
                view.findViewById<TextView>(R.id.gesamtwert).bringToFront()


                if (tmpBaum.kartenNr == "A1") view.findViewById<ImageView>(R.id.kartenbild)
                    .setImageResource(R.drawable.bergahorn)
                if (tmpBaum.kartenNr == "A2") view.findViewById<ImageView>(R.id.kartenbild)
                    .setImageResource(R.drawable.rotbuche)
                if (tmpBaum.kartenNr == "A3") view.findViewById<ImageView>(R.id.kartenbild)
                    .setImageResource(R.drawable.apfel)
                if (tmpBaum.kartenNr == "A4") view.findViewById<ImageView>(R.id.kartenbild)
                    .setImageResource(R.drawable.pappel)

                if (tmpBaum.kartenNr == "B1") view.findViewById<ImageView>(R.id.kartenbild)
                    .setImageResource(R.drawable.kastanie)
                if (tmpBaum.kartenNr == "B2") view.findViewById<ImageView>(R.id.kartenbild)
                    .setImageResource(R.drawable.esche)
                if(tmpBaum.kartenNr == "B3") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.goldregen)
                if(tmpBaum.kartenNr == "B4") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.robinie)

                if (tmpBaum.kartenNr == "C1") view.findViewById<ImageView>(R.id.kartenbild)
                    .setImageResource(R.drawable.zypresse)
                if (tmpBaum.kartenNr == "C2") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.schwerinskiefer)
                if (tmpBaum.kartenNr == "C3") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.tamariske)
                if (tmpBaum.kartenNr == "C4") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.kasuarine)

                if (tmpBaum.kartenNr == "D1") view.findViewById<ImageView>(R.id.kartenbild)
                    .setImageResource(R.drawable.eibe3)
                if(tmpBaum.kartenNr == "D2") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.fichte)
                if(tmpBaum.kartenNr == "D3") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.douglasie)
                if(tmpBaum.kartenNr == "D4") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.andentanne)

                if(tmpBaum.kartenNr == "E1") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.mammutbaum)
                if(tmpBaum.kartenNr == "E2") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.lebensbaum)

                if(tmpBaum.kartenNr == "F1") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.kiefer)
                if(tmpBaum.kartenNr == "F2") view.findViewById<ImageView>(R.id.kartenbild).setImageResource(R.drawable.laerche)

                if (aktSpieler == 1) {
                    player1.setImageResource(R.drawable.playeractive)
                    player.setImageResource(R.drawable.player1)
                } else {
                    player.setImageResource(R.drawable.playeractive)
                    player1.setImageResource(R.drawable.player1)
                }

            }
            //kartenRuecken.animate().translationX(0F).setDuration(1000)
        }

        fun initDraw()
        {
            if (aktSpieler == 1) {
                if(spielerBlatt1.size<4 && SpielEinstellungen.KartenDurchschalten)
                {
                    prevcard_btn.isVisible = true
                    nextcard_btn.isVisible = true
                } else {
                    prevcard_btn.isVisible = false
                    nextcard_btn.isVisible = false
                }
                karteZeichnen(spielerBlatt1.get(Spieler1i))

            };
            if (aktSpieler == 2) {
                if(spielerBlatt2.size<4 && SpielEinstellungen.KartenDurchschalten)
                {
                    prevcard_btn.isVisible = true
                    nextcard_btn.isVisible = true
                } else {
                    prevcard_btn.isVisible = false
                    nextcard_btn.isVisible = false
                }
                karteZeichnen(spielerBlatt2.get(Spieler2i))
            };
        }
        initDraw()


        fun initWechsel() {
            kartenRuecken.setImageResource(R.drawable.card_back)
            kartenbild.isVisible = false
            baumart.isVisible = false
            gesamtwert.isVisible = false
            blattart_text.isVisible = false
            kartennr.isVisible = false
            baumname_text.isVisible = false
            baumgattung_text.isVisible = false
            beschreibungView.isVisible = false
            stat1.isVisible = false
            stat2.isVisible = false
            stat3.isVisible = false
            stat4.isVisible = false
            d("FUNKTION","InitWechsel")
            if (initSpielerwechsel == 1) {
                playerSwitchDialog.setTitle("Spielerwechsel")
                if(SpielEinstellungen.vsAI && aktSpieler== 2) playerSwitchDialog.setMessage("Computer an der Reihe!")
                else playerSwitchDialog.setMessage("Spieler " + aktSpieler + " an der Reihe!")
                playerSwitchDialog.setNeutralButton("OK") { _, _ ->
                    initSpielerwechsel = 0
                    initDraw()
                }
            }
            val dialog: AlertDialog = playerSwitchDialog.create()
            dialog.show()
        }



        view.findViewById<ImageButton>(R.id.nextcard_btn).setOnClickListener {
            if(aktSpieler==1)
            {
                if(Spieler1i <  (spielerBlatt1.size - 1))
                {
                    Spieler1i++
                    initDraw()
                }
                initDraw()
            }
            else
            {
                if(Spieler2i <  (spielerBlatt2.size - 1))
                {
                    Spieler2i++
                    initDraw()
                }
            }


        }

        view.findViewById<ImageButton>(R.id.prevcard_btn).setOnClickListener {
            if(aktSpieler == 1 && Spieler1i >= 1) {
                    Spieler1i--
                    initDraw()
                }
            if(aktSpieler == 2 && Spieler2i >=1)
            {
                Spieler2i--
                initDraw()
            }
        }

        view.findViewById<FloatingActionButton>(R.id.button_second).setOnClickListener {
            timer.cancel()
            mediaPlayer?.stop()
            findNavController().navigate(R.id.toSuperTrumpfSettings)
        }

        view.findViewById<FloatingActionButton>(R.id.holzwertspielen_button).setOnClickListener {

            playerSwitchDialog.setTitle("Holzwert gespielt")
            playerSwitchDialog.setMessage(""+SpielEinstellungen.Spieler1+ " - "+ spielerBlatt1[Spieler1i].name+ " : " + spielerBlatt1[Spieler1i].holzwert + "\n\n<vs>\n\n"+SpielEinstellungen.Spieler2+ " - "  + spielerBlatt2[Spieler2i].name+ " : " + spielerBlatt2[Spieler2i].holzwert)

            if(spielerBlatt1[Spieler1i].holzwert>spielerBlatt2[Spieler2i].holzwert)
            {
                unentschiedenCounter=0;
                playerSwitchDialog.setNeutralButton("Spieler 1 gewinnt") { _, _ ->

                    spielerBlatt1.add(spielerBlatt2[Spieler2i])
                    spielerBlatt2.removeAt(Spieler2i)
                    if(Spieler1i<spielerBlatt1.size-1) Spieler1i++ else Spieler1i=0
                    if(Spieler2i<spielerBlatt2.size-1) Spieler2i++ else Spieler2i=0
                    if(aktSpieler == 2)
                    {
                        aktSpieler = 1
                        initSpielerwechsel = 1;
                        initWechsel()
                    } else
                    {
                        initDraw()
                        mediaPlayer = MediaPlayer.create(context,R.raw.success)
                        if(SpielEinstellungen.Ton) mediaPlayer?.start()
                    }

                }

            }

            if(spielerBlatt1[Spieler1i].holzwert<spielerBlatt2[Spieler2i].holzwert)
            {
                unentschiedenCounter=0;
                playerSwitchDialog.setNeutralButton("Spieler 2 gewinnt") { _, _ ->
                    spielerBlatt2.add(spielerBlatt1[Spieler1i])
                    spielerBlatt1.removeAt(Spieler1i)
                    if(Spieler1i<spielerBlatt1.size-1) Spieler1i++ else Spieler1i=0
                    if(Spieler2i<spielerBlatt2.size-1) Spieler2i++ else Spieler2i=0

                    if(aktSpieler == 1)
                    {
                        aktSpieler = 2
                        initSpielerwechsel = 1;
                        initWechsel()
                    } else {
                        initDraw()
                        mediaPlayer = MediaPlayer.create(context,R.raw.success)
                        if(SpielEinstellungen.Ton && SpielEinstellungen.vsAI==false) mediaPlayer?.start()
                    }

                }
            }
            if(spielerBlatt1[Spieler1i].holzwert==spielerBlatt2[Spieler2i].holzwert)
            {
                unentschiedenCounter++;
                playerSwitchDialog.setNeutralButton("Unentschieden") { _, _ ->
                    if(Spieler1i<spielerBlatt1.size-1) Spieler1i++ else Spieler1i=0
                    if(Spieler2i<spielerBlatt2.size-1) Spieler2i++ else Spieler2i=0
                    initDraw()
                }
            }
            val dialog: AlertDialog = playerSwitchDialog.create()
            dialog.show()
        }

        view.findViewById<FloatingActionButton>(R.id.groeßespielen_button).setOnClickListener {

            playerSwitchDialog.setTitle("Größe gespielt")
            playerSwitchDialog.setMessage(""+SpielEinstellungen.Spieler1+ " - "+ spielerBlatt1[Spieler1i].name+ " : " + spielerBlatt1[Spieler1i].maxWuchshoehe + "\n" +
                    "\n" +
                    "<vs>\n" +
                    "\n"+SpielEinstellungen.Spieler2+ " - "  + spielerBlatt2[Spieler2i].name+ " : " + spielerBlatt2[Spieler2i].maxWuchshoehe)

            if(spielerBlatt1[Spieler1i].maxWuchshoehe>spielerBlatt2[Spieler2i].maxWuchshoehe)
            {
                unentschiedenCounter=0;
                playerSwitchDialog.setNeutralButton("Spieler 1 gewinnt") { _, _ ->

                    spielerBlatt1.add(spielerBlatt2[Spieler2i])
                    spielerBlatt2.removeAt(Spieler2i)
                    if(Spieler1i<spielerBlatt1.size-1) Spieler1i++ else Spieler1i=0
                    if(Spieler2i<spielerBlatt2.size-1) Spieler2i++ else Spieler2i=0
                    if(aktSpieler == 2)
                    {
                        aktSpieler = 1
                        initSpielerwechsel = 1;
                        initWechsel()
                    } else
                    {
                        initDraw()
                        mediaPlayer = MediaPlayer.create(context,R.raw.success)
                        if(SpielEinstellungen.Ton) mediaPlayer?.start()
                    }


                }

            }

            if(spielerBlatt1[Spieler1i].maxWuchshoehe<spielerBlatt2[Spieler2i].maxWuchshoehe)
            {
                unentschiedenCounter=0;
                playerSwitchDialog.setNeutralButton("Spieler 2 gewinnt") { _, _ ->
                    spielerBlatt2.add(spielerBlatt1[Spieler1i])
                    spielerBlatt1.removeAt(Spieler1i)
                    if(Spieler1i<spielerBlatt1.size-1) Spieler1i++ else Spieler1i=0
                    if(Spieler2i<spielerBlatt2.size-1) Spieler2i++ else Spieler2i=0

                    if(aktSpieler == 1)
                    {
                        aktSpieler = 2
                        initSpielerwechsel = 1;
                        initWechsel()
                    } else
                    {
                        initDraw()
                        mediaPlayer = MediaPlayer.create(context,R.raw.success)
                        if(SpielEinstellungen.Ton && SpielEinstellungen.vsAI==false) mediaPlayer?.start()
                    }

                }
            }
            if(spielerBlatt1[Spieler1i].maxWuchshoehe==spielerBlatt2[Spieler2i].maxWuchshoehe)
            {
                unentschiedenCounter++;
                playerSwitchDialog.setNeutralButton("Unentschieden") { _, _ ->
                    if(Spieler1i<spielerBlatt1.size-1) Spieler1i++ else Spieler1i=0
                    if(Spieler2i<spielerBlatt2.size-1) Spieler2i++ else Spieler2i=0
                    initDraw()
                }
            }
            val dialog: AlertDialog = playerSwitchDialog.create()
            dialog.show()
        }

        view.findViewById<FloatingActionButton>(R.id.seltenheitspielen_button2).setOnClickListener {

            playerSwitchDialog.setTitle("Seltenheit gespielt")
            playerSwitchDialog.setMessage(""+SpielEinstellungen.Spieler1+ " - "+ spielerBlatt1[Spieler1i].name+ " : " + spielerBlatt1[Spieler1i].seltenheit + "\n" +
                    "\n" +
                    "<vs>\n" +
                    "\n"+SpielEinstellungen.Spieler2+ " - "  + spielerBlatt2[Spieler2i].name+ " : " + spielerBlatt2[Spieler2i].seltenheit)

            if(spielerBlatt1[Spieler1i].seltenheit>spielerBlatt2[Spieler2i].seltenheit)
            {
                unentschiedenCounter=0;
                playerSwitchDialog.setNeutralButton("Spieler 1 gewinnt") { _, _ ->

                    spielerBlatt1.add(spielerBlatt2[Spieler2i])
                    spielerBlatt2.removeAt(Spieler2i)
                    if(Spieler1i<spielerBlatt1.size-1) Spieler1i++ else Spieler1i=0
                    if(Spieler2i<spielerBlatt2.size-1) Spieler2i++ else Spieler2i=0
                    if(aktSpieler == 2)
                    {
                        aktSpieler = 1
                        initSpielerwechsel = 1;
                        initWechsel()
                    } else
                    {
                        initDraw()
                        mediaPlayer = MediaPlayer.create(context,R.raw.success)
                        if(SpielEinstellungen.Ton) mediaPlayer?.start()
                    }


                }

            }

            if(spielerBlatt1[Spieler1i].seltenheit<spielerBlatt2[Spieler2i].seltenheit)
            {
                unentschiedenCounter=0;
                playerSwitchDialog.setNeutralButton("Spieler 2 gewinnt") { _, _ ->
                    spielerBlatt2.add(spielerBlatt1[Spieler1i])
                    spielerBlatt1.removeAt(Spieler1i)
                    if(Spieler1i<spielerBlatt1.size-1) Spieler1i++ else Spieler1i=0
                    if(Spieler2i<spielerBlatt2.size-1) Spieler2i++ else Spieler2i=0

                    if(aktSpieler == 1)
                    {
                        aktSpieler = 2
                        initSpielerwechsel = 1;
                        initWechsel()
                    } else
                    {
                        initDraw()
                        mediaPlayer = MediaPlayer.create(context,R.raw.success)
                        if(SpielEinstellungen.Ton && SpielEinstellungen.vsAI==false) mediaPlayer?.start()
                    }

                }
            }
            if(spielerBlatt1[Spieler1i].seltenheit==spielerBlatt2[Spieler2i].seltenheit)
            {
                unentschiedenCounter++;
                playerSwitchDialog.setNeutralButton("Unentschieden") { _, _ ->
                    if(Spieler1i<spielerBlatt1.size-1) Spieler1i++ else Spieler1i=0
                    if(Spieler2i<spielerBlatt2.size-1) Spieler2i++ else Spieler2i=0
                    initDraw()
                }
            }
            val dialog: AlertDialog = playerSwitchDialog.create()
            dialog.show()
        }
        view.findViewById<FloatingActionButton>(R.id.giftspielen_button).setOnClickListener {

            playerSwitchDialog.setTitle("Giftigkeit gespielt")
            playerSwitchDialog.setMessage(""+SpielEinstellungen.Spieler1+ " - "+ spielerBlatt1[Spieler1i].name+ " : " + spielerBlatt1[Spieler1i].giftigkeit + "\n" +
                    "\n" +
                    "<vs>\n" +
                    "\n"+SpielEinstellungen.Spieler2+ " - "  + spielerBlatt2[Spieler2i].name+ " : " + spielerBlatt2[Spieler2i].giftigkeit)

            if(spielerBlatt1[Spieler1i].giftigkeit>spielerBlatt2[Spieler2i].giftigkeit)
            {
                unentschiedenCounter=0;
                playerSwitchDialog.setNeutralButton("Spieler 1 gewinnt") { _, _ ->

                    spielerBlatt1.add(spielerBlatt2[Spieler2i])
                    spielerBlatt2.removeAt(Spieler2i)
                    if(Spieler1i<spielerBlatt1.size-1) Spieler1i++ else Spieler1i=0
                    if(Spieler2i<spielerBlatt2.size-1) Spieler2i++ else Spieler2i=0
                    if(aktSpieler == 2)
                    {
                        aktSpieler = 1
                        initSpielerwechsel = 1;
                        initWechsel()
                    } else
                    {
                        initDraw()
                        mediaPlayer = MediaPlayer.create(context,R.raw.success)
                        if(SpielEinstellungen.Ton) mediaPlayer?.start()
                    }


                }

            }

            if(spielerBlatt1[Spieler1i].giftigkeit<spielerBlatt2[Spieler2i].giftigkeit)
            {
                unentschiedenCounter=0;
                playerSwitchDialog.setNeutralButton("Spieler 2 gewinnt") { _, _ ->
                    spielerBlatt2.add(spielerBlatt1[Spieler1i])
                    spielerBlatt1.removeAt(Spieler1i)
                    if(Spieler1i<spielerBlatt1.size-1) Spieler1i++ else Spieler1i=0
                    if(Spieler2i<spielerBlatt2.size-1) Spieler2i++ else Spieler2i=0

                    if(aktSpieler == 1)
                    {
                        aktSpieler = 2
                        initSpielerwechsel = 1;
                        initWechsel()
                    } else
                    {
                        initDraw()
                        mediaPlayer = MediaPlayer.create(context,R.raw.success)
                        if(SpielEinstellungen.Ton && SpielEinstellungen.vsAI==false) mediaPlayer?.start()
                    }

                }
            }
            if(spielerBlatt1[Spieler1i].giftigkeit==spielerBlatt2[Spieler2i].giftigkeit)
            {
                unentschiedenCounter++;
                playerSwitchDialog.setNeutralButton("Unentschieden") { _, _ ->
                    if(Spieler1i<spielerBlatt1.size-1) Spieler1i++ else Spieler1i=0
                    if(Spieler2i<spielerBlatt2.size-1) Spieler2i++ else Spieler2i=0
                    initDraw()
                }
            }
            val dialog: AlertDialog = playerSwitchDialog.create()
            dialog.show()
        }
    }
}
