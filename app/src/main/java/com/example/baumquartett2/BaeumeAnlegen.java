package com.example.baumquartett2;

import java.util.ArrayList;
import java.util.List;

       public class BaeumeAnlegen {

            public List<Baum> lBaum = new ArrayList<>();

            public List<Baum> BaeumeFuellen()
            {
                Baum Ahorn = new Baum("A1","Der Berg-Ahorn ist eine Pflanzenart aus der Gattung der Ahorne. Um seine Zugehörigkeit zur Gattung der Ahorne zu betonen, ist in der Botanik die Bindestrichschreibweise üblich und nicht die ansonsten geläufige Schreibweise Bergahorn.",
                        "Berg Ahorn","Laubbaum", "Acer pseudoplatanus","Einzelblatt",0, 4,2,3,2,-24,-24,1,2,0);
                lBaum.add(Ahorn);

                Baum Buche = new Baum("A2","Die Buchen sind die einzige Pflanzengattung der Unterfamilie der Fagoideae innerhalb der Familie der Buchengewächse. Die etwa elf Arten besitzen eine weite Verbreitung in den gemäßigten Gebieten der Nordhalbkugel in Nordamerika und Eurasien."
                        ,"Gemeine Buche","Laubbaum", "Fagus sylvatica","Einzelblatt",0,2,2,4,2,-24,-32,2,1,1);
                lBaum.add(Buche);

                Baum Apfel = new Baum("A3","Der Kulturapfel ist eine weithin bekannte Art aus der Gattung der Äpfel in der Familie der Rosengewächse. Er ist eine wirtschaftlich sehr bedeutende Obstart. Die Frucht des Apfelbaumes wird Apfel genannt. Äpfel werden sowohl als Nahrungsmittel im Obstbau als auch zur Zierde angepflanzt."
                        ,"Kulturapfel","Laubbaum", "Malus domestica","Einzelblatt",0,2,1,4,2,-24,-32,2,3,0);
                lBaum.add(Apfel);

                Baum Pappel = new Baum("A4","Die Grau-Pappel ist ein Laubbaum aus der Gattung der Pappeln. Sie ist eine natürliche Hybride aus Silber-Pappel und Zitter-Pappel."
                        ,"Grau-Pappel","Laubbaum", "Populus x canescens","Einzelblatt",1,2,2,4,2,-24,-32,2,1,0);
                lBaum.add(Pappel);

                Baum Kastanie = new Baum("B1","Die Gewöhnliche Rosskastanie, auch Gemeine Rosskastanie oder Weiße Rosskastanie genannt, ist eine auf dem Balkan heimische, in Mitteleuropa verbreitet angepflanzte Art der Gattung Rosskastanien. In Deutschland wurde die Gewöhnliche Rosskastanie zum Baum des Jahres 2005 gewählt.","Gemeine Rosskastanie","Laubbaum", "Aesculus hippocastanum","Zusammengesetztes Blatt",2,2,2,4,2,-24,-32,2,2,1);
                lBaum.add(Kastanie);

                Baum Esche = new Baum("B2","Die Gemeine Esche, Gewöhnliche Esche oder Hohe Esche ist eine in Europa heimische Baumart, die mit einer Wuchshöhe von bis zu etwa 40 m zu den höchsten Laubbäumen Europas zählt; an exponierteren Standorten erreicht sie jedoch oftmals nur um 15 bis 20 m."
                        ,"Gemeine Esche","Laubbaum", "Fraxinus excelsior","Zusammengesetztes Blatt",0,2,2,0);
                lBaum.add(Esche);

                Baum Goldregen = new Baum("B3","Der Gemeine Goldregen, auch Gewöhnlicher Goldregen genannt, ist eine Pflanzenart aus der Gattung Goldregen. Sie ist giftig."
                        ,"Gemeiner Goldregen","Laubbaum", "Laburnum anagyroides","Zusammengesetztes Blatt",1,0,0,3);
                lBaum.add(Goldregen);

                Baum Robinie = new Baum("B4","Die Gewöhnliche Robinie, auch verkürzt Robinie, Weiße Robinie, Falsche Akazie, Scheinakazie, Gemeiner Schotendorn oder Silberregen genannt, ist ein sommergrüner Laubbaum. Sie stammt aus Nordamerika und wird überall in Europa erst seit fast 400 Jahren in Parks und Gärten gepflanzt. Sie wächst inzwischen auch wild."
                        ,"Robinie","Laubbaum", "Robinia pseudoacacia","Zusammengesetztes Blatt",1,1,2,3);
                lBaum.add(Robinie);

                Baum Zypresse = new Baum("C1","Die Weiße Scheinzypresse ist eine Pflanzenart aus der Familie der Zypressengewächse. Sie ist in den Küstenregionen der östlichen und südöstlichen USA heimisch."
                        ,"Weiße Scheinzypresse","Laubbaum", "Chamaecyparis thyoides","Nadelförmig",0,1,3,0);
                lBaum.add(Zypresse);

                Baum SchwerinsKiefer = new Baum("C2","Um 1905 entstanden im Park des Grafen Schwerin in Wendisch-Wilmersdorf bei Berlin, aus einer Kreuzung von Pinus strobus und Pinus wallichian. Nicht selten in Parks gepflanzt."
                        ,"Schwerins Kiefer","Laubbaum", "Pinus x schwerinii","Nadelförmig",1,1,3,0);
                lBaum.add(SchwerinsKiefer);

                Baum Tamariske = new Baum("C3","Tamarix-Arten wachsen als kleine, gut verzweigte, xeromorphe, häufig laubabwerfende Bäume und Sträucher, die Wuchshöhen von meist 1 bis 10, selten bis 18 Meter erreichen. Es sind tief wurzelnde Pflanzen."
                        ,"Frühlings-Tamariske","Laubbaum", "Tamarix parviflora","Nadelförmig",1,0,0,0);
                lBaum.add(Tamariske);

                Baum Kasuarine = new Baum("C4","Kasuarinen (Casuarina) sind eine Pflanzengattung aus der Ordnung der Buchenartigen (Fagales). Gelegentlich wird der deutsche Begriff Kasuarine allerdings auch für die ebenfalls zu den Kasuarinengewächsen gehörenden Gattungen Allocasuarina und Gymnostoma verwendet."
                        ,"Kasuarine ","Laubbaum", "Casuarina stricta","Nadelförmig",2,1,2,0);
                lBaum.add(Kasuarine);


                Baum Eibe = new Baum("D1","Die Europäische Eibe, auch Gemeine Eibe oder nur Eibe genannt, früher auch Bogenbaum, Eue, Eve, Ibe, If, Ifen, ist die einzige europäische Art in der Pflanzengattung der Eiben. Sie ist die älteste und schattenverträglichste Baumart Europas. Sie kann ein sehr hohes Alter erreichen. "
                        ,"Gemeine Eibe","Nadelbaum", "Taxus baccata","Einzelnadel",2,2,1,4,2,-24,-32,2,3,3);
                lBaum.add(Eibe);

                Baum Fichte = new Baum("D2","Die Gemeine Fichte, auch Gewöhnliche Fichte, Rotfichte oder Rottanne genannt, ist eine Pflanzenart in der Gattung der Fichten. Sie ist in Europa und bis weit in das kontinentale Asien heimisch und die einzige in Mitteleuropa natürlich vorkommende Vertreterin der Gattung."
                        ,"Gemeine Fichte","Nadelbaum", "Picea abies","Einzelnadel",0,3,0,0);
                lBaum.add(Fichte);

                Baum Douglasie = new Baum("D3","Die Gewöhnliche Douglasie, oft einfach nur Douglasie oder umgangssprachlich auch Douglastanne, Douglasfichte, Douglaskiefer bzw. nach der Herkunft Oregon pine genannt, ist eine Pflanzenart aus der Gattung der Douglasien."
                        ,"Douglasie","Nadelbaum", "Pseudotsuga menziesii","Einzelnadel",1,3,3,0);
                lBaum.add(Douglasie);

                Baum Andentanne = new Baum("D4","Beheimatet in Südamerika in Chile und Argentinien auf Hügellandschaften und vulkanischer Hänge bis über 1500 m Höhe. Der erste Baum wurde 1705 nach Europa eingeführt. Heutzutage in vielen wintermilden Parks und Gärten anzutreffen."
                        ,"Andentanne","Nadelbaum", "Araucaria araucana","Einzelnadel",2,1,3,0);
                lBaum.add(Andentanne);

                Baum Mammut = new Baum("E1","Der Riesenmammutbaum ist die einzige Art in der monotypischen Pflanzengattung Sequoiadendron in der Unterfamilie der Mammutbäume innerhalb der Familie der Zypressengewächse. Der Riesenmammutbaum ist im natürlichen Verbreitungsgebiet eine vom Aussterben bedrohte Art."
                    ,"Riesenmammutbaum","Nadelbaum", "Sequoiadendron giganteum","Schuppenförmig",3,3,1,0);
                lBaum.add(Mammut);

                Baum Lebensbaum = new Baum("E2","Lebensbäume oder Thujen sind eine Pflanzengattung in der Familie der Zypressengewächse innerhalb der Ordnung der Kiefernartigen. Es gibt zwei Arten in Nordamerika und drei im östlichen Asien."
                        ,"Lebensbaum","Nadelbaum", "Thuja occidentalis","Schuppenförmig",0,0,0,3);
                lBaum.add(Lebensbaum);

                Baum Kiefer = new Baum("F1","Die Waldkiefer, auch Gewöhnliche oder Gemeine Kiefer, Rotföhre, Weißkiefer oder Forche genannt, ist eine Pflanzenart in der Gattung der Kiefern aus der Familie der Kieferngewächse. Sie ist heute, nach der Gemeinen Fichte, die zweithäufigste Baumart in den deutschen Wäldern."
                        ,"Wald-Kiefer","Nadelbaum", "Pinus sylvestris","Einzelnadel",0,3,1,0);
                lBaum.add(Kiefer);

                Baum Laerche = new Baum("F2","Die Europäische Lärche ist eine Pflanzenart aus der Gattung der Lärchen (Larix) in der Familie der Kieferngewächse (Pinaceae). Ihre natürlichen Verbreitungsgebiete sind hauptsächlich die Alpen und einige Gebiete in den Karpaten. Sie überdauerte die letzte Eiszeit vermutlich in den Karpaten."
                        ,"Europäische Lärche","Nadelbaum", "Larix decidua","Einzelnadel",1,2,1,0);
                lBaum.add(Laerche);


                return lBaum;
            }

            //public List<Baum> baeumeUebergeben() {return lBaum;};
        }

