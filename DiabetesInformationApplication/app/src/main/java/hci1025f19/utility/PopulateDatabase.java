package hci1025f19.utility;

import android.content.Context;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import hci1025f19.database.Food;
import hci1025f19.database.FrequentlyAskedQuestions;
import hci1025f19.database.Identifier;
import hci1025f19.database.Profile;

public class PopulateDatabase {

    /**
     * Method to populate Database with default Food items first time the app is launched
     */
    public static void populateDB(Context context) {

        String random = android.os.Build.SERIAL.toString();

        Identifier identifier = new Identifier();
        identifier.set_ID(random);
        //Identifier to determine if it is the advanced UCI part of the application they have access to
        identifier.set_advanced(true);
        identifier.set_hotfix(true);
        identifier.save();

        //region Food region
        //http://maddata.dk/
        // Food(String name, Double carbohydrate, Double protein, Double fiber, Double sugar)
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Tørkage, linse",443.0,5.6,48.9,20.0,1.3,24.5));
        foods.add(new Food("Lammekød, slag, råt",364.0,14.5,0.1,0.0,0.0,34.5));
        foods.add(new Food("Kylling, kød og skind, friturestegt",259.0,28.3,0.1,0.0,0.0,16.3));
        foods.add(new Food("Brie, 50+",328.0,19.8,0.8,0.0,0.0,27.8));
        foods.add(new Food("Østers, rå",62.0,7.8,4.1,0.0,0.0,1.5));
        foods.add(new Food("Rygeost, 1 % fedt",69.0,11.9,4.4,0.0,0.0,0.5));
        foods.add(new Food("Kalvekød, fedt, råt",272.0,17.3,0.0,0.0,0.0,22.8));
        foods.add(new Food("Æble, dansk (sort: stark earliest), råt",43.0,0.3,8.7,0.0,2.1,0.3));
        foods.add(new Food("Burger med lille bøf, salat og dressing, fastfood",226.0,9.8,22.5,1.3,10.4,0.0));
        foods.add(new Food("Due, vildt, kød, rå",141.0,18.5,0.0,0.0,0.0,7.5));
        foods.add(new Food("Hytteost, 20+",105.0,13.3,3.4,0.0,0.0,4.4));
        foods.add(new Food("Peber, hvid",354.0,10.4,70.2,0.0,4.3,2.1));
        foods.add(new Food("Kartoffel, efterår (oktober til januar), rå",87.0,2.2,18.1,0.0,1.4,0.2));
        foods.add(new Food("Gærekstrakt, marmite",154.0,27.8,8.8,0.0,3.0,0.0));
        foods.add(new Food("Cacaosmør",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Wienerpølse, ristet, fastfood",289.0,14.1,2.2,0.0,0.2,25.2));
        foods.add(new Food("Rapsolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Pølse, cervelatpølse, pålæg",274.0,11.1,4.5,0.0,0.3,23.7));
        foods.add(new Food("Hvedebrød, bolle, italiensk type, industrifremstillet",269.0,8.8,51.2,0.0,2.9,1.8));
        foods.add(new Food("Kylling, brystfilet, kød, i lage, rå",98.0,20.0,0.6,1.0,0.0,1.7));
        foods.add(new Food("Torsk, rogn, rå",121.0,23.2,1.1,0.0,0.0,2.6));
        foods.add(new Food("Bitter, Gammel Dansk Bitter Dram",211.0,0.0,0.0,0.5,0.0,0.0));
        foods.add(new Food("Hvidkål, rå",32.0,1.2,5.1,0.0,2.3,0.2));
        foods.add(new Food("Gin",263.0,0.0,0.0,0.0,0.0,0.0));
        foods.add(new Food("Mayonnaise, fedtreduceret",454.0,0.0,0.0,0.0,0.0,51.3));
        foods.add(new Food("Fersken, rå",49.0,0.7,9.8,0.0,1.7,0.4));
        foods.add(new Food("Samsø, 45+",359.0,27.0,1.6,0.0,0.0,27.9));
        foods.add(new Food("Bouillon, hønsekød, spiseklar",4.0,0.3,0.3,0.0,0.2,0.0));
        foods.add(new Food("Bønnespirer, soja, rå",57.0,6.2,4.5,0.0,0.8,1.4));
        foods.add(new Food("Hvedebrød, kastanjebrød",261.0,8.0,44.3,0.0,4.7,4.1));
        foods.add(new Food("Æg, høne, blomme, tørret",650.0,34.5,2.5,0.0,0.0,56.5));
        foods.add(new Food("Kylling, kød og skind, rå",173.0,18.0,0.0,0.0,0.0,11.3));
        foods.add(new Food("Æg, høne, kogt, dybfrost",145.0,13.7,1.5,0.0,9.5,0.0));
        foods.add(new Food("Gulerod, juice, konserves",41.0,1.0,8.5,0.0,0.8,0.2));
        foods.add(new Food("Pizza med tunfisk, dybfrost",204.0,11.0,25.9,2.1,5.7,0.0));
        foods.add(new Food("Fiskefilet, paneret, friturestegt, fastfood",287.0,13.4,21.9,1.0,16.1,0.0));
        foods.add(new Food("Majs, kerner, dybfrost",113.0,3.3,20.5,0.0,2.2,1.4));
        foods.add(new Food("Oksekød, spidsbryst, råt",212.0,17.6,0.5,0.0,0.0,15.6));
        foods.add(new Food("Ørred, dam-, sø-, rå",145.0,18.7,0.0,0.0,0.0,7.8));
        foods.add(new Food("Grønkål, rå",60.0,4.7,4.7,0.0,6.2,1.2));
        foods.add(new Food("Peber, chili, rå",45.0,2.0,7.7,0.0,1.8,0.2));
        foods.add(new Food("Trekantsandwich med skinke og ost, salat og dressing, fastfood",229.0,10.4,21.2,1.7,11.1,0.0));
        foods.add(new Food("Baked beans i tomatsovs, konserves",89.0,5.1,12.5,6.6,0.5,0.0));
        foods.add(new Food("Roulade, hindbær-",342.0,3.6,59.8,1.0,9.3,0.0));
        foods.add(new Food("Fiskeolie, hydrogeneret",883.0,0.0,0.0,0.0,0.0,99.9));
        foods.add(new Food("Spidskål, rå",27.0,2.1,3.0,0.0,1.9,0.3));
        foods.add(new Food("Pommes frites, friturestegt, fastfood",311.0,3.7,39.0,0.0,3.2,14.8));
        foods.add(new Food("Bacon, stegestykke, rå",424.0,13.0,0.0,0.0,0.0,42.0));
        foods.add(new Food("Salat, endivie, kruset (frisee), rå",19.0,1.3,2.4,0.0,0.9,0.2));
        foods.add(new Food("Skærekage, sandkage",446.0,6.1,48.7,1.0,25.0,0.0));
        foods.add(new Food("Oksekød, uspec., helt magert, råt",125.0,21.5,0.0,0.0,0.0,4.3));
        foods.add(new Food("Sødmælkspulver",493.0,23.9,41.1,0.0,0.0,26.1));
        foods.add(new Food("Yoghurt med kirsebær",90.0,3.4,12.1,4.5,0.5,3.1));
        foods.add(new Food("Sukker, brunt rørsukker",405.0,0.5,99.3,99.3,0.0,0.0));
        foods.add(new Food("Flødeost, 70+",375.0,9.6,2.7,0.0,0.0,36.9));
        foods.add(new Food("Pitasandwich med salat og dressing, fastfood",150.0,3.6,19.6,1.9,5.9,0.0));
        foods.add(new Food("Spædbørnsmad, modermælkserstatning, lavt jernindhold, pulver",476.0,9.5,58.0,0.0,27.7,0.0));
        foods.add(new Food("Marmelade, engelsk type",281.0,0.1,68.7,65.0,0.7,0.0));
        foods.add(new Food("Roulade, abrikos-",330.0,3.6,61.0,1.0,7.3,0.0));
        foods.add(new Food("Hamburgerryg, røget",118.0,16.7,1.7,1.1,0.0,4.9));
        foods.add(new Food("Solbærgele",246.0,0.5,59.0,57.0,0.0,0.5));
        foods.add(new Food("Malakoffpølse, pålæg",271.0,9.8,5.1,0.2,23.7,0.0));
        foods.add(new Food("Hummer, rå",94.0,19.6,0.7,0.0,0.0,1.3));
        foods.add(new Food("Tomatsuppe, spiseklar",73.0,1.6,12.7,0.4,1.5,0.0));
        foods.add(new Food("Karry, pulver",340.0,12.7,25.2,0.0,33.2,13.8));
        foods.add(new Food("Risdrik, tilsat calcium",55.0,0.1,11.4,0.0,0.9,0.0));
        foods.add(new Food("Skinke, røget, kogt",198.0,18.3,0.0,0.0,0.0,14.0));
        foods.add(new Food("Småkage, vanillekranse",527.0,4.7,61.1,2.0,28.8,0.0));
        foods.add(new Food("Burger med kylling, salat og dressing, fastfood",207.0,9.5,19.2,1.5,9.9,0.0));
        foods.add(new Food("Spinat, hakket, dybfrost",26.0,2.1,2.2,0.0,2.0,0.5));
        foods.add(new Food("Parmesan, 32+",356.0,33.6,2.2,0.0,0.0,24.1));
        foods.add(new Food("Kaffe, instant, decaffeinated, drikkeklar",4.0,0.1,0.8,0.0,0.0,0.0));
        foods.add(new Food("Cantaloupe (melon), rå",36.0,0.8,7.3,0.0,0.9,0.2));
        foods.add(new Food("Citron, rå",45.0,0.5,8.7,0.0,1.2,1.1));
        foods.add(new Food("Syre (krydderplante), rå",21.0,2.0,0.3,0.0,2.9,0.7));
        foods.add(new Food("Chokolade, fløde, med nødder",573.0,8.8,50.7,40.0,0.0,37.5));
        foods.add(new Food("Esrom, 60+",421.0,19.4,1.4,0.0,0.0,38.3));
        foods.add(new Food("Snacks, ekspanderet, majsbasis",503.0,7.4,58.8,3.6,25.7,0.0));
        foods.add(new Food("Krydder",389.0,9.3,71.4,0.0,3.3,5.8));
        foods.add(new Food("Paksoi, pak-choi, pai tsai, rå",15.0,1.5,1.2,0.0,1.0,0.2));
        foods.add(new Food("Bulgur (hvedekerner, knækkede, parboiled), rå",359.0,10.8,68.0,0.0,7.7,2.3));
        foods.add(new Food("Reje, dybvands-, kogt, dybfrost",69.0,15.3,0.0,0.0,0.0,0.8));
        foods.add(new Food("Yoghurt, med frugt, 0.1 % fedt, uden tilsat sukker",44.0,3.9,6.7,0.0,0.1,0.0));
        foods.add(new Food("Salt, havsalt (ikke jodberiget)",0.0,0.0,0.0,0.0,0.0,0.0));
        foods.add(new Food("Blåbær, frosne, usukrede",54.0,0.4,10.7,0.0,1.5,0.6));
        foods.add(new Food("Birkes, frø",543.0,18.0,13.7,10.0,44.7,0.0));
        foods.add(new Food("Hvidvin, Rhinskvin",67.0,0.1,2.8,0.0,0.0,0.0));
        foods.add(new Food("Gaffelbidder",190.0,12.0,0.0,8.0,0.0,16.0));
        foods.add(new Food("Grisekam uden svær, ca. 3 mm spæk, rå",195.0,19.2,0.0,0.0,0.0,13.3));
        foods.add(new Food("Ål, rå",306.0,15.1,0.0,0.0,0.0,27.7));
        foods.add(new Food("Jordnøddesmør",630.0,22.6,8.5,0.0,7.6,53.7));
        foods.add(new Food("Kommen, rå",330.0,19.8,8.3,38.0,14.6,0.0));
        foods.add(new Food("Græskar, konserves",105.0,0.4,24.7,0.0,0.9,0.1));
        foods.add(new Food("Jordbær, syltede",205.0,0.3,49.0,46.0,1.0,0.3));
        foods.add(new Food("Flødeost, 60+",330.0,10.2,2.7,0.0,0.0,31.5));
        foods.add(new Food("Rosenkål, efterår (oktober til december), rå",57.0,4.8,6.3,0.0,4.1,0.5));
        foods.add(new Food("Lime, rå",37.0,0.7,7.7,0.0,2.8,0.2));
        foods.add(new Food("Frugtsaft, blandet, usødet, koncentreret",35.0,0.2,8.5,0.0,0.0,0.0));
        foods.add(new Food("Margarine, 70 %, bordbrug, blød, vegetabilsk fedt",671.0,0.8,10.6,0.0,0.0,70.6));
        foods.add(new Food("Spirituosa, gennemsnitlige værdier",231.0,0.0,0.0,0.0,0.0,0.0));
        foods.add(new Food("Sild, paneret, stegt, i lage",272.0,12.8,23.5,0.2,14.0,0.0));
        foods.add(new Food("Sandwich med kylling og bacon, salat og dressing, fastfood",206.0,11.1,23.3,2.0,7.0,0.0));
        foods.add(new Food("Hvidvin, uspec.",82.0,0.1,2.6,0.0,0.0,10.3));
        foods.add(new Food("Nyre, okse, rå",107.0,15.6,4.6,0.0,0.0,2.8));
        foods.add(new Food("Salt, havsalt (jodberiget)",0.0,0.0,0.0,0.0,0.0,0.0));
        foods.add(new Food("Agurk, syltet",14.0,0.3,2.1,15.0,1.2,0.2));
        foods.add(new Food("Creme fraiche 18 %",190.0,2.8,3.6,0.0,0.0,18.6));
        foods.add(new Food("Camembert, 45+",286.0,21.4,0.6,0.0,0.0,22.4));
        foods.add(new Food("Broccoli, rå",38.0,5.3,1.3,0.0,3.2,0.6));
        foods.add(new Food("Aborre, rå",78.0,16.4,0.0,0.0,0.0,1.3));
        foods.add(new Food("Grisekød, hakket, 5-10% fedt, råt",173.0,18.4,0.0,0.0,0.0,11.2));
        foods.add(new Food("Pizza med broccoli, dybfrost",195.0,8.7,23.6,2.4,6.7,0.0));
        foods.add(new Food("Tomat, flået, konserves",21.0,1.2,3.0,0.0,0.9,0.3));
        foods.add(new Food("Mandarin, rå",49.0,0.7,10.2,0.0,1.2,0.3));
        foods.add(new Food("Kyllingepølse, pålæg",186.0,13.5,2.6,0.5,0.2,13.6));
        foods.add(new Food("Grisekød, filet, 0-1 mm fedtkant, rå",131.0,21.9,0.0,0.0,4.7,0.0));
        foods.add(new Food("Yoghurt naturel, sødmælk",62.0,3.8,4.1,0.0,0.0,3.6));
        foods.add(new Food("Kvark, 45+",195.0,11.2,3.7,0.0,0.0,15.4));
        foods.add(new Food("Oksekød, tyndsteg med mørbrad, rå",210.0,20.3,0.0,0.0,0.0,14.4));
        foods.add(new Food("Nougat",549.0,8.7,55.9,0.4,32.3,0.0));
        foods.add(new Food("Småkage, klejne, industrifremstillet",565.0,5.4,50.5,1.5,37.7,0.0));
        foods.add(new Food("Røde linser, tørrede, rå",361.0,27.3,52.4,6.2,2.9,0.0));
        foods.add(new Food("Blomkål, dansk, rå",24.0,2.0,1.9,0.0,2.4,0.4));
        foods.add(new Food("Hvedekerner, hele/knækkede",344.0,10.7,63.6,0.0,11.0,2.0));
        foods.add(new Food("Cashewnød, olieristet",605.0,16.8,23.6,0.0,3.3,47.8));
        foods.add(new Food("Frølår, dybfrost",70.0,16.4,0.2,0.0,0.0,0.3));
        foods.add(new Food("Brosme, rå",80.0,19.0,0.0,0.0,0.0,0.3));
        foods.add(new Food("Kiks, dobbelte, hvede, søde",483.0,5.7,68.6,2.2,19.7,0.0));
        foods.add(new Food("Skærekage, formkage, uspec.",424.0,4.5,54.9,1.3,20.2,0.0));
        foods.add(new Food("Æg, høne, røræg mix, industrifremstillet",132.0,10.9,3.8,0.0,8.2,0.0));
        foods.add(new Food("Tørkage, uspec.",450.0,6.0,56.6,20.0,2.5,21.3));
        foods.add(new Food("Broccoli, kogt",35.0,2.4,3.9,3.3,0.4,0.0));
        foods.add(new Food("Laks, atlantisk, opdræt, rå",191.0,19.5,0.0,0.0,0.0,12.6));
        foods.add(new Food("Oksekød, tykstegsfilet uden kappe, rå",123.0,21.8,0.0,0.0,0.0,3.9));
        foods.add(new Food("Kalkun, kød, rå",106.0,21.3,0.0,0.0,0.0,2.2));
        foods.add(new Food("Lucernespirer, rå",27.0,4.0,0.2,0.0,1.9,0.7));
        foods.add(new Food("Grisemørbrad, afpudset, rå",116.0,21.2,0.1,0.0,0.0,3.3));
        foods.add(new Food("Lever, okse, rå",123.0,20.3,3.4,0.0,0.0,3.0));
        foods.add(new Food("Pop corn, mikroovn",504.0,9.6,52.2,0.0,8.1,26.8));
        foods.add(new Food("Solbærsaft, sød, koncentreret",201.0,0.5,48.0,46.0,0.0,0.5));
        foods.add(new Food("Dressing, mayonnaise",343.0,1.2,12.6,0.0,32.5,0.0));
        foods.add(new Food("Peber, chili, konserves",27.0,0.9,4.9,0.0,1.2,0.1));
        foods.add(new Food("Oksekød, klump uden kappe, rå",132.0,20.4,0.0,0.0,0.0,5.6));
        foods.add(new Food("Hvedebrød, italiensk type, stort, detailbageri",253.0,8.0,49.1,0.0,3.1,1.3));
        foods.add(new Food("Agurk, syltet, uden tilsat sukker",10.0,0.3,1.1,0.0,1.2,0.2));
        foods.add(new Food("Yoghurt, med frugt, 1.5 % fedt",74.0,3.6,12.0,4.0,0.0,1.3));
        foods.add(new Food("Ost, fast, 45+, alle typer",333.0,24.7,1.9,0.0,0.0,25.7));
        foods.add(new Food("Grisefedt, afsmeltet, Raffinol",875.0,0.0,0.0,0.0,0.0,99.0));
        foods.add(new Food("Flødeis",207.0,4.0,25.1,18.0,0.0,10.0));
        foods.add(new Food("Kødpølse, fedtreduceret, pålæg",106.0,10.0,8.3,0.0,0.6,3.4));
        foods.add(new Food("Kumquat, guldorange, dværgappelsin, rå",65.0,1.1,9.5,0.0,4.4,1.5));
        foods.add(new Food("Rype, kød, rå",114.0,23.5,0.1,0.0,0.0,2.0));
        foods.add(new Food("Oksekød, højrebsfilet med kappe, rå",232.0,19.5,0.0,0.0,0.0,17.3));
        foods.add(new Food("Gulerod, dybfrost",30.0,1.1,4.7,0.0,2.3,0.2));
        foods.add(new Food("Blåbær, rå",52.0,0.7,10.4,0.0,1.5,0.5));
        foods.add(new Food("Kylling, vinge, kød og skind, rå",186.0,19.2,0.1,0.0,0.0,12.2));
        foods.add(new Food("Porre, rå",36.0,1.9,5.1,0.0,2.2,0.4));
        foods.add(new Food("Skinke, yderlår, helt afpudset, råt",116.0,20.2,0.0,0.0,0.0,3.9));
        foods.add(new Food("Ispind, vanille med chokoladeovertræk",274.0,3.0,23.0,20.0,0.0,19.0));
        foods.add(new Food("Smørbart blandingsprodukt, let, 57% fedt",532.0,0.4,0.6,0.0,59.7,0.0));
        foods.add(new Food("Sprængt nakke, grisekød",262.0,19.5,0.1,0.0,20.6,0.0));
        foods.add(new Food("Flødeskumskage, kartoffelkage",375.0,5.2,55.0,0.5,14.7,0.0));
        foods.add(new Food("Tomat, importeret, rå",33.0,1.1,5.1,0.0,2.1,0.4));
        foods.add(new Food("Brasen, rå",108.0,16.7,1.2,0.0,0.0,4.0));
        foods.add(new Food("Hyldebærsaft, sød, koncentreret",223.0,0.5,53.2,41.0,0.0,0.5));
        foods.add(new Food("Flæskesvær, snacks",576.0,54.1,0.0,0.0,40.3,0.0));
        foods.add(new Food("Peberrod, rå",105.0,7.7,13.0,0.0,7.5,0.7));
        foods.add(new Food("Myseost, 33+",432.0,15.0,32.9,0.0,0.0,27.0));
        foods.add(new Food("Salat, Romaine, romersk, rå",16.0,1.2,1.2,0.0,2.1,0.3));
        foods.add(new Food("Medisterpølse, rå",213.0,12.3,3.3,0.2,16.8,0.0));
        foods.add(new Food("Cacaomælk",70.0,3.7,9.6,4.5,0.5,1.8));
        foods.add(new Food("Dressing, blue cheese",512.0,4.8,7.4,0.0,52.3,0.0));
        foods.add(new Food("Grovfranskbrød",262.0,8.3,46.2,0.0,4.2,3.3));
        foods.add(new Food("Krydderskinke, udbenet",202.0,12.0,4.6,0.0,15.2,0.0));
        foods.add(new Food("Sild, marineret, i madeirasauce",231.0,14.0,8.0,8.0,0.0,16.0));
        foods.add(new Food("Reje, dybvands-, konserves",80.0,16.5,0.7,0.0,0.0,1.2));
        foods.add(new Food("Hirse, hele korn",360.0,11.0,63.5,0.0,8.5,4.2));
        foods.add(new Food("Jordnøddeolie",753.0,0.0,27.5,0.0,0.0,72.5));
        foods.add(new Food("Pizza romana, dybfrost",178.0,7.2,23.1,1.9,5.8,0.0));
        foods.add(new Food("Hjerte, okse, råt",103.0,17.7,1.0,0.0,0.0,3.1));
        foods.add(new Food("Kaffe, instant, pulver",303.0,16.5,42.5,0.0,19.1,1.7));
        foods.add(new Food("Tyttebær, rå",54.0,0.8,10.4,0.0,2.5,0.5));
        foods.add(new Food("Grisebryst, kogestykke uden svær, ca. 2 mm spæk, rå",226.0,17.4,0.0,0.0,0.0,17.6));
        foods.add(new Food("Oksekød, højrebsfilet, afpudset, rå",135.0,22.4,0.0,0.0,0.0,5.0));
        foods.add(new Food("Olivenolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Boghvedemel",357.0,6.6,75.5,0.0,1.9,2.0));
        foods.add(new Food("Vandmelon, rå",33.0,0.8,7.0,0.0,0.5,0.1));
        foods.add(new Food("Müsli uden tilsat sukker.",372.0,12.0,60.9,8.8,6.3,0.0));
        foods.add(new Food("Mineralvand, sodavand, uden tilsat sukker, uspec.",4.0,0.0,1.0,0.0,0.0,0.0));
        foods.add(new Food("Lammekød, bov, rå",229.0,18.5,0.0,0.0,0.0,17.4));
        foods.add(new Food("Lakrids, salt",360.0,6.7,79.8,36.0,0.0,1.0));
        foods.add(new Food("Minarine, 40 %, vegetabilsk fedt",368.0,0.5,0.1,0.0,0.0,41.3));
        foods.add(new Food("Flødeskumskage, walesbolle/-kringle",322.0,4.1,24.9,0.9,22.9,0.0));
        foods.add(new Food("Skinkemignon (lårtunge), helt afpudset, rå",118.0,19.3,0.0,0.0,0.0,4.4));
        foods.add(new Food("Kyllingenuggets, friturestegt, fastfood",280.0,14.7,19.1,0.0,16.1,0.0));
        foods.add(new Food("Peber, sort",305.0,11.0,44.5,0.0,26.5,3.3));
        foods.add(new Food("Sandwich med æg / rejer, salat og dressing, fastfood",187.0,7.8,24.1,1.5,6.2,0.0));
        foods.add(new Food("Grana, 32+",352.0,33.6,1.7,0.0,0.0,24.1));
        foods.add(new Food("Oksekød, tyndbryst, råt",274.0,7.0,0.0,0.0,0.0,27.8));
        foods.add(new Food("Høne, kød og skind, rå",212.0,17.1,0.6,0.0,0.0,15.9));
        foods.add(new Food("Citronsaft, friskpresset",32.0,0.4,8.6,0.0,0.0,0.0));
        foods.add(new Food("Portvin",153.0,0.1,11.1,11.0,0.0,0.0));
        foods.add(new Food("Fennikel, frø",323.0,15.8,12.5,0.0,39.8,14.9));
        foods.add(new Food("Pizza med kød og fisk, tomat og ost, fastfood",229.0,12.8,22.4,2.1,9.3,0.0));
        foods.add(new Food("Grisekød, uspec., magert, råt",115.0,21.8,0.0,0.0,0.0,3.0));
        foods.add(new Food("Kylling, grillstegt, fastfood",212.0,28.0,0.0,0.0,11.2,0.0));
        foods.add(new Food("Yoghurt med peach melba",88.0,3.3,11.5,6.1,0.5,3.2));
        foods.add(new Food("Kylling, ben, kød og skind, rå",152.0,18.4,0.0,0.0,0.0,8.7));
        foods.add(new Food("Kødextrakt, terning",240.0,16.0,23.7,0.0,0.0,8.9));
        foods.add(new Food("Oksekød, uspec., middelfedt, råt",242.0,19.7,8.9,0.0,0.0,14.2));
        foods.add(new Food("Dragé, lakrids",379.0,2.0,87.5,0.0,1.7,0.0));
        foods.add(new Food("Franskbrød",274.0,8.8,47.6,0.0,3.6,3.9));
        foods.add(new Food("Brisling (sardin), i tomat, konserves",205.0,15.1,0.5,0.0,0.0,16.0));
        foods.add(new Food("Litchi, lichiblomme, kinesisk blomme, rå",72.0,0.8,15.2,0.0,1.3,0.4));
        foods.add(new Food("Tomatketchup",114.0,2.5,18.9,3.3,2.4,0.0));
        foods.add(new Food("Jordnød, tørret",535.0,25.8,15.9,0.0,7.7,37.8));
        foods.add(new Food("Grisekød, uspec., fedt, råt",341.0,14.4,0.0,0.0,0.0,32.0));
        foods.add(new Food("Rugbrød, fuldkorn, industrifremstillet",201.0,5.1,37.0,8.5,1.4,0.0));
        foods.add(new Food("Pølse, bratwurst, thüringer",280.0,14.4,1.1,0.0,0.2,24.5));
        foods.add(new Food("Skummetmælk, 0.5 % fedt",38.0,3.5,4.8,0.0,0.0,0.5));
        foods.add(new Food("Sukker, brun farin",388.0,0.0,95.5,94.0,0.0,0.0));
        foods.add(new Food("Jordbærsaft, sur, koncentreret",35.0,0.5,7.0,0.0,0.0,0.5));
        foods.add(new Food("Asparges, grønne, rå",26.0,1.8,3.1,0.0,1.8,0.3));
        foods.add(new Food("Hvedebrød, sandwich/toastbrød med fedtrige frø, industrifremstillet",270.0,8.1,43.6,0.0,3.8,5.6));
        foods.add(new Food("Agurk, rå",12.0,0.7,1.7,0.0,0.7,0.1));
        foods.add(new Food("Ost, fast, 20+, alle typer",224.0,30.0,1.7,0.0,0.0,11.0));
        foods.add(new Food("Gås, kød og skind, rå",361.0,15.8,0.0,0.0,0.0,33.6));
        foods.add(new Food("Spidskommen, rå",426.0,17.8,30.6,10.5,22.3,0.0));
        foods.add(new Food("Kiks, rug",448.0,6.1,68.3,6.1,14.9,0.0));
        foods.add(new Food("Kokosnød, rå",379.0,4.4,1.9,0.0,14.0,36.5));
        foods.add(new Food("Helt, rå",114.0,21.6,0.0,0.0,0.0,3.0));
        foods.add(new Food("Nyre, kalv, rå",96.0,16.4,3.8,0.0,0.0,1.6));
        foods.add(new Food("Laks, Stillehavs-, vild, rå",106.0,21.2,0.0,0.0,0.0,2.3));
        foods.add(new Food("Rullepølse, pålæg",230.0,14.5,0.0,0.5,0.0,19.4));
        foods.add(new Food("Flødeyoghurt med frugt, 9 % fedt",128.0,3.0,11.6,7.6,0.5,7.7));
        foods.add(new Food("Surbrød",252.0,7.0,46.4,0.0,3.7,2.8));
        foods.add(new Food("Cacaoskummetmælk",59.0,3.5,9.8,4.8,0.5,0.5));
        foods.add(new Food("Hirseflager",374.0,12.1,71.0,0.0,2.0,3.3));
        foods.add(new Food("Solbær, rå",75.0,1.5,11.4,0.0,5.8,1.3));
        foods.add(new Food("Tyggegummi, uden sukker, uspec.",347.0,0.6,83.6,1.0,2.4,0.0));
        foods.add(new Food("Gedde, rå",91.0,19.0,0.7,0.0,0.0,1.2));
        foods.add(new Food("Tomat, soltørret",284.0,14.1,43.5,12.3,3.0,0.0));
        foods.add(new Food("Creme fraiche 38 %",357.0,2.1,2.5,0.0,0.0,38.4));
        foods.add(new Food("Druesaft",64.0,0.6,14.8,0.0,0.1,0.1));
        foods.add(new Food("Frugtpålæg, uspec.",322.0,2.7,61.1,0.0,7.9,5.6));
        foods.add(new Food("Havkat, plettet, rå",107.0,16.0,0.0,0.0,0.0,4.8));
        foods.add(new Food("Isvaffel med vaniljeis, uspec.",277.0,3.9,35.0,0.0,13.4,0.0));
        foods.add(new Food("Is, 3. farvet",198.0,4.0,22.9,18.0,0.0,10.0));
        foods.add(new Food("Kirsebærsaft, sur, koncentreret",59.0,0.5,13.0,0.0,0.0,0.5));
        foods.add(new Food("Leverpostej",237.0,11.1,6.3,1.0,0.4,18.7));
        foods.add(new Food("Yoghurt med jordbær",85.0,3.3,10.9,6.4,0.5,3.1));
        foods.add(new Food("Roquefort, 50+",340.0,19.4,0.0,0.0,0.0,29.5));
        foods.add(new Food("Risklid",395.0,13.4,28.0,0.0,21.0,20.9));
        foods.add(new Food("Sesamfrø, hele, tørrede",571.0,17.7,3.2,0.0,18.0,49.7));
        foods.add(new Food("Lactose, pulver",398.0,0.0,97.8,0.0,0.1,0.0));
        foods.add(new Food("Tørkage, mazariner",476.0,5.7,48.7,22.0,3.0,27.9));
        foods.add(new Food("Ribssaft, sød, koncentreret",173.0,0.5,41.0,40.0,0.0,0.5));
        foods.add(new Food("Salat, egeløv, rå",15.0,1.4,1.5,0.0,1.3,0.2));
        foods.add(new Food("Musling, rå",83.0,11.9,3.7,0.0,0.0,2.2));
        foods.add(new Food("Hvedeklid",290.0,16.2,24.8,0.0,40.2,5.3));
        foods.add(new Food("Gær, tørret",317.0,32.5,29.9,0.0,19.7,1.5));
        foods.add(new Food("Kylling, kød, rå",128.0,19.3,0.0,0.0,0.0,5.6));
        foods.add(new Food("Honning",332.0,0.3,81.5,0.0,0.0,0.0));
        foods.add(new Food("Klipfisk, rå",101.0,24.3,0.0,0.0,0.0,0.2));
        foods.add(new Food("Ananaskirsebær, kapstikkelbær, rå",41.0,0.9,5.9,0.0,4.3,0.6));
        foods.add(new Food("Paté, pålæg",250.0,12.8,2.7,1.0,0.2,21.1));
        foods.add(new Food("Svenbo, 45+",374.0,27.6,1.6,0.0,0.0,29.2));
        foods.add(new Food("Laks, atlantisk, vild, koldrøget",162.0,21.4,0.1,0.0,0.0,8.4));
        foods.add(new Food("Flødeskumskage, gåsebryst",342.0,3.1,41.5,0.5,18.1,0.0));
        foods.add(new Food("Jordbær, dybfrost, sukrede",85.0,0.5,19.0,11.9,1.9,0.2));
        foods.add(new Food("Kammerjunkere",468.0,5.4,69.7,2.0,17.8,0.0));
        foods.add(new Food("Sapodille (chiku), rå",81.0,0.4,14.7,0.0,5.3,1.1));
        foods.add(new Food("Havregryn, uspec.",369.0,12.9,57.4,0.0,9.9,6.9));
        foods.add(new Food("Konfektureprodukt, skumvare, uspec.",350.0,5.2,79.0,0.1,0.9,0.0));
        foods.add(new Food("Gulerod, uspec., rå",36.0,0.7,6.0,0.0,2.9,0.4));
        foods.add(new Food("Solbærmarmelade",202.0,0.5,47.1,45.0,2.5,0.4));
        foods.add(new Food("Sardin, i olie, konserves",205.0,24.6,1.1,0.0,0.0,11.3));
        foods.add(new Food("Flødeskumskage, uspec.",310.0,2.7,28.1,1.5,20.6,0.0));
        foods.add(new Food("Pizza napolitana, dybfrost",182.0,8.6,21.9,2.1,6.1,0.0));
        foods.add(new Food("Hvedebrød, groft, fiberbrød",219.0,9.8,36.4,0.0,5.2,1.9));
        foods.add(new Food("Grisekød, uspec., ca. 10 % fedt, råt",182.0,18.7,0.0,0.0,0.0,12.0));
        foods.add(new Food("Gedemælk",69.0,3.6,4.5,0.0,0.0,4.1));
        foods.add(new Food("Rugbrød med fedtrige frø og kerner, industrifremstillet",215.0,5.7,33.3,0.0,8.8,4.3));
        foods.add(new Food("Snaps, 40 % vol., uspec.",222.0,0.0,0.0,0.0,0.0,0.0));
        foods.add(new Food("Gulerod, konserves",24.0,0.6,4.0,0.0,1.5,0.2));
        foods.add(new Food("Æble, uspec., råt",56.0,0.3,12.1,0.0,2.2,0.2));
        foods.add(new Food("Wienerbrød, kanelsnegl",417.0,4.5,47.6,3.0,3.0,22.5));
        foods.add(new Food("Ananasjuice, konserves",55.0,0.4,12.8,0.1,0.1,0.0));
        foods.add(new Food("Daddel, tørret",313.0,2.0,71.2,0.0,6.4,0.5));
        foods.add(new Food("Hytteost, 5+",77.0,13.6,3.0,0.0,1.2,0.0));
        foods.add(new Food("Yoghurt med skovbær",87.0,3.3,11.4,4.7,0.5,3.1));
        foods.add(new Food("Valnøddeolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Kakifrugt (Sharon), rå",88.0,0.7,17.5,0.0,5.9,0.3));
        foods.add(new Food("Ribssaft, sur, koncentreret",39.0,0.5,8.0,0.0,0.0,0.5));
        foods.add(new Food("Kylling, kød og skind, paneret, friturestegt",283.0,22.5,9.1,0.0,0.3,17.4));
        foods.add(new Food("Oksekød, mørbrad, afpudset, rå",138.0,20.0,0.0,0.0,0.0,6.4));
        foods.add(new Food("Fruktose",405.0,0.0,99.8,95.0,0.0,0.0));
        foods.add(new Food("Rabarber, rå",26.0,0.9,3.0,0.0,3.8,0.3));
        foods.add(new Food("Ost, fast, 40+, alle typer",310.0,25.7,1.9,0.0,0.0,22.6));
        foods.add(new Food("Rødvin, uspec.",77.0,0.2,2.5,0.0,0.0,0.0));
        foods.add(new Food("Torsk, filet, kogt",77.0,17.6,0.0,0.0,0.0,0.6));
        foods.add(new Food("Gruyere, 45+",393.0,29.9,2.0,0.0,0.0,30.1));
        foods.add(new Food("Æg, gås, rå",179.0,13.9,1.4,0.0,0.0,13.3));
        foods.add(new Food("Mælkebøtte, blade, rå",58.0,3.0,8.1,0.0,1.9,1.0));
        foods.add(new Food("Fersken, konserves",72.0,0.5,16.2,7.6,1.2,0.2));
        foods.add(new Food("Pecannød, tørristet",730.0,9.5,2.5,0.0,9.4,74.3));
        foods.add(new Food("Kylling, lår, kød og skind, rå",155.0,18.7,0.0,0.0,0.0,8.9));
        foods.add(new Food("Hvedemel, bagerimel",353.0,10.0,71.1,2.9,1.6,0.0));
        foods.add(new Food("Løg, ristede",567.0,6.0,22.1,0.0,15.0,48.0));
        foods.add(new Food("Brisling (ansjovis), marineret, konserves",214.0,13.4,11.0,10.0,0.0,13.0));
        foods.add(new Food("Blomkål, uspec., rå",28.0,2.4,2.5,0.0,2.2,0.4));
        foods.add(new Food("Oliven, sorte, uden sten, i saltlage",111.0,1.0,2.9,4.3,9.8,0.0));
        foods.add(new Food("Hummer, kogt",90.0,18.7,0.3,0.0,0.0,1.5));
        foods.add(new Food("Tidselolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Hvedebrød, italiensk, store, industrifremstillet",261.0,8.2,48.4,3.1,2.5,0.0));
        foods.add(new Food("Grønne ærter, dybfrost",74.0,6.1,8.4,0.0,4.7,0.7));
        foods.add(new Food("Jordskok, rå",80.0,2.1,15.1,0.0,2.6,0.6));
        foods.add(new Food("Buddingpulver",353.0,0.3,85.9,0.0,1.6,0.0));
        foods.add(new Food("Læskedrik, koncentreret (1+5), tilsat sukker, uspec.",256.0,0.0,63.0,62.4,0.0,0.0));
        foods.add(new Food("Likør, kaffe",344.0,0.1,46.8,46.8,0.0,0.3));
        foods.add(new Food("Fårekød, kølle, rå",135.0,20.1,0.0,0.0,0.0,6.0));
        foods.add(new Food("Knækbrød, rug-, groft",346.0,9.3,63.5,0.0,15.3,2.0));
        foods.add(new Food("Kiks, fuldkorns-, digestivetype",462.0,6.5,68.9,16.4,3.6,16.5));
        foods.add(new Food("Okra, gumbo, rå",36.0,2.0,6.1,0.0,0.9,0.1));
        foods.add(new Food("Byggryn, rå",353.0,9.2,67.2,0.0,9.5,2.4));
        foods.add(new Food("Havregryn, ikke beriget",368.0,13.3,57.0,0.0,10.3,6.7));
        foods.add(new Food("Pop corn, traditionelle",468.0,9.6,57.9,0.0,8.1,20.2));
        foods.add(new Food("Kuller, rå",78.0,17.9,0.0,0.0,0.0,0.6));
        foods.add(new Food("Margarine, gennemsnit",729.0,0.0,0.0,0.0,0.0,82.4));
        foods.add(new Food("Danbo, 30+",256.0,28.4,1.3,0.0,0.0,15.7));
        foods.add(new Food("Grisefilet, helt afpudset, rå",107.0,22.3,0.0,0.0,0.0,1.9));
        foods.add(new Food("Pekannød, olieristet",735.0,9.2,1.9,0.0,9.5,75.2));
        foods.add(new Food("Rødspætte, filet, paneret, friturestegt",252.0,15.2,17.7,0.0,1.0,13.2));
        foods.add(new Food("Sælkød, råt",115.0,22.6,0.0,0.0,2.6,0.0));
        foods.add(new Food("Bacon i skiver, rå",328.0,14.8,0.0,0.5,0.0,30.3));
        foods.add(new Food("Laks, atlantisk, opdræt, varmrøget",252.0,22.7,0.0,0.0,18.1,0.0));
        foods.add(new Food("Rødfisk, lille, filet, rå",91.0,19.1,0.0,0.0,0.0,1.5));
        foods.add(new Food("Pitasandwich med kebab, salat og dressing, fastfood",173.0,9.1,16.6,1.6,7.4,0.0));
        foods.add(new Food("Flødeskumskage, medalje",369.0,3.6,38.8,0.9,22.0,0.0));
        foods.add(new Food("Miso, sojapasta",167.0,10.5,18.1,0.0,5.4,4.6));
        foods.add(new Food("Loquat, japansk mispel, rå",49.0,0.4,10.4,0.0,1.7,0.2));
        foods.add(new Food("Netmelon, rå",24.0,0.8,4.2,0.0,1.0,0.2));
        foods.add(new Food("Spegepølse",459.0,14.5,0.0,0.0,0.0,45.2));
        foods.add(new Food("Bondebrød med fedtrige frø, industrifremstillet",237.0,7.5,36.5,0.0,8.0,4.6));
        foods.add(new Food("Kålrabi (kålroe), rå",34.0,1.0,5.8,0.0,2.9,0.1));
        foods.add(new Food("Fløde 18 %",188.0,2.7,4.4,0.0,0.0,18.0));
        foods.add(new Food("Milkshake",87.0,5.1,12.5,0.0,1.8,0.0));
        foods.add(new Food("Gulerod, importeret, rå",39.0,1.0,6.3,0.0,3.0,0.4));
        foods.add(new Food("Flødeboller, skumboller",428.0,4.2,65.1,64.0,0.4,16.5));
        foods.add(new Food("Tebirkes",433.0,7.6,40.6,3.0,25.9,0.0));
        foods.add(new Food("Champignon, konserves",22.0,2.5,0.7,0.0,2.4,0.5));
        foods.add(new Food("Blomme, rå",48.0,0.5,10.5,0.0,1.6,0.3));
        foods.add(new Food("Kalkunkød, hakket, 0-5% fedt, råt",132.0,22.1,0.0,0.0,0.0,4.8));
        foods.add(new Food("Grønkål, dybfrost",29.0,2.1,2.1,0.0,3.4,0.6));
        foods.add(new Food("Fiskeboller, konserves",62.0,8.3,4.9,0.0,0.0,1.0));
        foods.add(new Food("Tykmælk",63.0,3.5,4.6,0.0,0.0,3.6));
        foods.add(new Food("Parmesan, revet",426.0,38.5,4.8,0.0,0.0,28.6));
        foods.add(new Food("Risstivelse",348.0,0.8,84.9,0.0,0.0,0.0));
        foods.add(new Food("Lyssej, rå",72.0,17.4,0.0,0.0,0.0,0.2));
        foods.add(new Food("Risengryn, grødris, brune, rå",367.0,6.8,76.3,2.4,2.8,0.0));
        foods.add(new Food("Grønne bønner, rå",30.0,1.9,3.6,0.0,3.0,0.2));
        foods.add(new Food("And, kød, rå",127.0,18.3,1.8,0.0,0.0,5.1));
        foods.add(new Food("Vermouth, sød",145.0,0.0,14.5,10.0,0.0,0.0));
        foods.add(new Food("Bønnespirer, uspec., rå",37.0,3.0,5.1,0.0,1.2,0.3));
        foods.add(new Food("Hindbær, rå",51.0,1.4,6.2,0.0,4.4,1.4));
        foods.add(new Food("Pølse, bierwurst",227.0,14.6,3.7,0.0,0.2,17.2));
        foods.add(new Food("Oksekød, tykstegsfilet med kappe, rå",171.0,19.4,0.0,0.0,0.0,10.4));
        foods.add(new Food("Oksekød, entrecote med kappe, rå",262.0,18.2,0.0,0.0,0.0,21.3));
        foods.add(new Food("Rugkerner, hele/knækkede",335.0,9.0,60.8,0.0,14.8,2.3));
        foods.add(new Food("Knækbrød, hvede-, fint",402.0,11.3,66.1,0.0,4.8,8.4));
        foods.add(new Food("Hvidvin, tør",67.0,0.1,0.2,0.0,0.0,0.0));
        foods.add(new Food("Sigtebrød",252.0,7.6,45.8,0.0,3.7,2.8));
        foods.add(new Food("Kippers i olie, konserves",247.0,20.5,0.0,0.0,0.0,18.5));
        foods.add(new Food("Pecannød, tørret",709.0,9.2,2.6,0.0,9.6,72.0));
        foods.add(new Food("Lever, gåse, rå",130.0,16.4,6.3,0.0,0.0,4.3));
        foods.add(new Food("Småkage, med lavt sukkerindhold (sukkerfri, light) industrifremstillet",486.0,6.5,64.0,1.1,23.7,0.0));
        foods.add(new Food("Boghvede, hel, rå",366.0,12.6,67.5,4.0,3.7,0.0));
        foods.add(new Food("Kantarel, rå",40.0,1.5,2.5,0.0,5.0,1.3));
        foods.add(new Food("Papaya, mamaja, træmelon, rå",47.0,0.7,9.4,0.0,2.1,0.3));
        foods.add(new Food("Julesalat, rå",19.0,1.1,2.6,0.0,1.1,0.2));
        foods.add(new Food("Øl, let pilsner, 2.6 % vol.",26.0,0.2,2.7,0.0,0.0,0.0));
        foods.add(new Food("Spinat, rå",24.0,2.6,1.3,0.0,1.9,0.6));
        foods.add(new Food("Yoghurt naturel, 0.1 % fedt",44.0,4.3,5.0,0.0,0.7,0.0));
        foods.add(new Food("Mandel, rå",587.0,21.2,4.3,0.0,12.5,49.9));
        foods.add(new Food("Rismel",365.0,7.3,78.9,0.0,0.8,1.3));
        foods.add(new Food("Oksekød, inderlår uden kappe, rå",113.0,22.3,0.0,0.0,0.0,2.5));
        foods.add(new Food("Kaktusfigen, rå",39.0,0.7,6.0,0.0,3.6,0.5));
        foods.add(new Food("Æble, dansk, råt",46.0,0.3,9.3,0.0,2.2,0.3));
        foods.add(new Food("Øl, ubeskattet, uspec.",46.0,0.3,8.3,0.0,0.0,0.0));
        foods.add(new Food("Blåmusling, kogt",113.0,17.8,4.1,0.0,0.0,2.8));
        foods.add(new Food("Vindruer, grønne (hvide), rå",70.0,0.5,15.1,0.0,1.4,0.4));
        foods.add(new Food("Margarine, 80 %, stegning/bagning, vegetabilsk fedt",713.0,0.1,0.9,0.0,0.0,80.2));
        foods.add(new Food("Sorbitol",239.0,0.0,100.0,0.0,0.0,0.0));
        foods.add(new Food("Hjerte, kalv, råt",107.0,14.9,3.3,0.0,0.0,3.7));
        foods.add(new Food("Hvedebrød, groft, store, industrifremstillet",260.0,8.9,42.9,0.0,4.7,4.2));
        foods.add(new Food("Hindbærmarmelade",205.0,0.6,48.1,45.0,1.2,0.6));
        foods.add(new Food("Frikadelle, stegt, industrifremstillet",217.0,13.4,9.1,0.0,0.2,14.2));
        foods.add(new Food("Dressing, mayonnaise, kalorielet",177.0,1.0,13.6,0.0,13.4,0.0));
        foods.add(new Food("Tomat, uspec., rå",20.0,0.7,2.9,0.0,1.4,0.3));
        foods.add(new Food("Pomelo, rå",58.0,1.0,10.2,3.3,0.7,0.0));
        foods.add(new Food("Laks, atlantisk, vild, gravad",178.0,21.2,2.7,2.8,0.0,9.2));
        foods.add(new Food("Yams, rå",112.0,1.5,23.8,0.0,4.1,0.2));
        foods.add(new Food("Krebs, rå",69.0,14.6,1.2,0.0,0.0,0.5));
        foods.add(new Food("Kaffe, drikkeklar",1.0,0.1,0.1,0.0,0.0,0.0));
        foods.add(new Food("Abrikos, rå",41.0,1.3,7.3,0.0,1.6,0.3));
        foods.add(new Food("Ab-mælk, syrnet sødmælk",63.0,3.5,4.6,0.0,0.0,3.6));
        foods.add(new Food("Sukkerært (mangetout), rå",48.0,3.8,6.4,2.3,0.3,0.0));
        foods.add(new Food("Stenbider, rå",167.0,9.8,0.3,0.0,0.0,14.2));
        foods.add(new Food("Salat, Radicchio rosso, rå",24.0,1.4,3.6,0.0,0.9,0.3));
        foods.add(new Food("Sortmund, blåhvilling, filet, rå",78.0,17.5,0.0,0.0,0.0,0.8));
        foods.add(new Food("Hvid hvede brød, groft, store, detailbageri",255.0,8.9,43.2,6.2,3.2,0.0));
        foods.add(new Food("Stikkelsbær, rå",44.0,0.9,7.3,0.0,3.2,0.6));
        foods.add(new Food("Morgenmadsprodukt, Guldkorn",391.0,6.7,84.7,30.0,3.5,1.3));
        foods.add(new Food("Grisebryst, slag, rullesteg, råt",215.0,17.9,0.0,0.0,0.0,16.1));
        foods.add(new Food("Makrel, røget",297.0,18.0,0.0,0.0,0.0,25.3));
        foods.add(new Food("Kirsebær, rå",56.0,1.4,11.0,0.0,1.3,0.3));
        foods.add(new Food("Rygeost, 10 % fedt",129.0,8.6,3.4,0.0,0.0,9.1));
        foods.add(new Food("Wienerpølse, kogt, fastfood",259.0,12.7,6.6,0.0,0.2,20.3));
        foods.add(new Food("Krabbe, kogt",129.0,20.1,0.4,0.0,0.0,5.2));
        foods.add(new Food("Solbærsaft, sur, koncentreret",51.0,0.5,11.0,0.0,0.0,0.5));
        foods.add(new Food("Koldskål",72.0,3.3,10.6,0.0,1.9,0.0));
        foods.add(new Food("Solsikkefrø, afskallede, tørrede",643.0,19.9,14.4,0.0,2.7,56.4));
        foods.add(new Food("Hare, rå",114.0,21.6,1.8,0.0,0.0,2.1));
        foods.add(new Food("Leverpølse, pålæg",388.0,13.4,4.1,0.0,0.2,35.8));
        foods.add(new Food("Sojamel",449.0,40.7,16.5,0.0,10.4,22.2));
        foods.add(new Food("Salat, mayonnaise-, makrel-",393.0,8.9,2.5,0.3,39.1,0.0));
        foods.add(new Food("Sandwich med skinke og ost, toasted, fastfood",298.0,11.0,32.8,1.7,13.2,0.0));
        foods.add(new Food("Tyttebær, syltede",158.0,0.2,37.0,26.0,2.5,0.2));
        foods.add(new Food("Marmelade, uspec.",205.0,0.5,48.5,52.0,1.0,0.5));
        foods.add(new Food("Palmeolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Letmælk, økologisk",47.0,3.4,5.1,0.0,0.0,1.5));
        foods.add(new Food("Kiwi, rå",63.0,1.0,11.7,0.0,2.4,0.8));
        foods.add(new Food("Laks, atlantisk, vild, rå",159.0,18.4,4.5,0.0,0.0,7.5));
        foods.add(new Food("Sennep, brun, færdiglavet",122.0,5.0,16.7,5.0,3.2,3.1));
        foods.add(new Food("Forårsrulle, dybfrost",151.0,5.8,15.5,0.0,2.1,6.8));
        foods.add(new Food("Pære, konserves",71.0,0.3,15.7,10.0,2.4,0.2));
        foods.add(new Food("Grisebryst, stegestykke med svær, rå",353.0,15.0,0.0,0.0,0.0,33.1));
        foods.add(new Food("Pop Corn (poppede majskærner)",488.0,9.6,53.7,0.0,8.1,24.3));
        foods.add(new Food("Pølse, tepølse",471.0,10.9,0.0,0.0,0.0,48.3));
        foods.add(new Food("Rosévin",71.0,0.2,1.4,0.0,0.0,0.0));
        foods.add(new Food("Smelteost, 20+",226.0,28.4,0.8,0.0,0.0,12.4));
        foods.add(new Food("Hasselnød, tørret",622.0,13.3,15.2,0.0,8.2,54.4));
        foods.add(new Food("Ris, brune, rå",362.0,9.0,71.4,0.0,4.2,2.9));
        foods.add(new Food("Ananas, konserves",64.0,0.4,14.1,2.5,1.4,0.3));
        foods.add(new Food("Hvedegryn, mannagryn",358.0,9.2,74.1,0.0,2.7,1.2));
        foods.add(new Food("Babymajs, rå",37.0,2.3,4.6,2.3,0.5,0.0));
        foods.add(new Food("Smelteost, 45+",311.0,23.3,0.0,0.0,0.0,24.5));
        foods.add(new Food("Burger med bøf og ost, salat og dressing, fastfood",250.0,10.7,22.0,1.6,12.9,0.0));
        foods.add(new Food("Citronskal, rå",51.0,1.5,5.4,0.0,10.6,0.3));
        foods.add(new Food("Hvidvin, mousserende, champagne",76.0,0.3,1.4,0.0,0.0,0.0));
        foods.add(new Food("Pizza med tomat og ost, fastfood",323.0,13.3,35.7,2.4,13.5,0.0));
        foods.add(new Food("Fiskefars, rå",111.0,8.5,12.8,0.0,2.8,0.0));
        foods.add(new Food("Kebabmix med pommes frites, friturestegt, fastfood",293.0,8.9,23.4,3.2,17.6,0.0));
        foods.add(new Food("Kalvekød, helt magert, råt",93.0,18.1,0.0,0.0,0.0,2.2));
        foods.add(new Food("Brombær, rå",37.0,1.4,3.4,0.0,4.3,1.0));
        foods.add(new Food("Figen, rå",77.0,0.8,16.3,0.0,2.9,0.3));
        foods.add(new Food("Vodka",231.0,0.0,0.0,0.0,0.0,0.0));
        foods.add(new Food("Kaviar, uægte (stenbiderrogn)",106.0,12.9,0.8,0.0,0.0,5.7));
        foods.add(new Food("Oksekød, tykbryst, råt",227.0,15.9,0.0,0.0,0.0,18.4));
        foods.add(new Food("Mungbønner, tørrede, rå",327.0,23.9,47.2,0.0,15.0,1.2));
        foods.add(new Food("Rødvin, rosévin, uden alkohol",15.0,0.2,3.6,0.0,0.0,0.0));
        foods.add(new Food("Mintgele",273.0,0.5,65.5,56.0,0.0,0.5));
        foods.add(new Food("Oliven, grønne, marinerede, konserves",132.0,1.0,5.0,0.0,2.4,11.7));
        foods.add(new Food("Figen, tørret",295.0,3.3,62.9,0.0,9.3,0.9));
        foods.add(new Food("Camembert, 50+",303.0,17.5,0.0,0.0,0.0,26.2));
        foods.add(new Food("Rødspætte, filet, paneret, dybfrost",138.0,11.9,19.2,0.0,1.0,1.1));
        foods.add(new Food("Hvedebrød, pitabrød, fint",254.0,7.6,49.6,2.5,1.6,0.0));
        foods.add(new Food("Kokosmel",677.0,6.4,10.1,0.0,12.6,65.7));
        foods.add(new Food("Skinkeculotte (yderlår), ca. 5 mm spæk, råt",222.0,19.2,5.3,0.0,0.0,13.8));
        foods.add(new Food("Oksekød, mellemskært, råt",164.0,20.8,0.0,0.0,0.0,9.0));
        foods.add(new Food("Melboller, uspec.",183.0,5.4,12.8,0.0,3.2,11.6));
        foods.add(new Food("Wienerbrød, kringle/stang",451.0,4.3,43.8,3.0,28.1,0.0));
        foods.add(new Food("Tvebakker",389.0,9.3,71.4,0.0,3.3,5.8));
        foods.add(new Food("Tun, rå",119.0,26.8,0.0,0.0,0.0,1.1));
        foods.add(new Food("Hvedebrød, groft, store, detailbageri",259.0,8.4,46.1,4.3,3.0,0.0));
        foods.add(new Food("Abrikosmarmelade",224.0,0.4,53.7,46.0,1.3,0.2));
        foods.add(new Food("Cayote, Kayote, rå",19.0,0.8,2.8,0.0,1.7,0.1));
        foods.add(new Food("Broccoli, dybfrost",27.0,2.8,1.8,3.0,0.3,0.0));
        foods.add(new Food("Græskar, rå",18.0,0.6,3.1,0.0,1.1,0.1));
        foods.add(new Food("Salt, stensalt, køkkensalt",0.0,0.0,0.0,0.0,0.0,0.0));
        foods.add(new Food("Grisekød, bagskank uden svær, rå",143.0,18.9,0.0,0.0,0.0,7.5));
        foods.add(new Food("Pastiller, sukkerfri, uspec.",380.0,1.0,90.1,0.0,1.1,0.0));
        foods.add(new Food("Rugbrød, kun groft rugmel, med fedtrige frø, detailbageri",243.0,7.4,33.5,7.5,6.8,0.0));
        foods.add(new Food("Lever, gris, rå",123.0,21.1,1.9,0.0,0.0,3.4));
        foods.add(new Food("Skummetmælk",36.0,3.5,4.8,0.0,0.0,0.3));
        foods.add(new Food("Syltetøj, uspec.",271.0,0.6,65.7,65.7,1.0,0.0));
        foods.add(new Food("Havregryn, beriget",365.0,13.0,56.4,0.0,11.2,6.5));
        foods.add(new Food("Æg, høne, blomme, saltet, pasteuriseret",267.0,14.0,1.6,0.0,0.0,23.0));
        foods.add(new Food("Tunge, okse, rå",186.0,16.2,2.6,0.0,0.0,12.4));
        foods.add(new Food("Danbo, 45+",322.0,23.7,2.1,0.0,0.0,25.0));
        foods.add(new Food("Fynbo, 45+",354.0,25.9,1.5,0.0,0.0,27.7));
        foods.add(new Food("Majsmel",365.0,8.0,74.3,0.0,3.2,2.8));
        foods.add(new Food("Rugbrød, soft, uden fedtrige frø, uden kerner, industrifremstillet",212.0,5.0,40.3,7.3,1.4,0.0));
        foods.add(new Food("Hvedebrød, valnøddebrød",282.0,8.9,46.5,0.0,4.1,5.2));
        foods.add(new Food("Sauce, gravad laks",555.0,1.6,13.1,10.3,0.0,56.0));
        foods.add(new Food("Gule ærter (flækærter), rå",351.0,22.0,56.4,0.0,7.4,2.1));
        foods.add(new Food("Oksekød, slag, råt",332.0,16.1,0.0,0.0,0.0,30.1));
        foods.add(new Food("Kinaradise, japanræddike, rå",17.0,0.6,2.6,0.0,1.6,0.1));
        foods.add(new Food("Modermælk, moden",71.0,1.3,7.2,0.0,4.1,0.0));
        foods.add(new Food("Margarine, 80 %, bordbrug, blød, vegetabilsk fedt",715.0,0.8,1.4,0.0,0.0,79.8));
        foods.add(new Food("Æg, høne, frilands høns, rå",136.0,12.3,1.2,0.0,9.2,0.0));
        foods.add(new Food("Rugbrød, mørkt, industrifremstillet",200.0,5.3,36.7,8.5,1.3,0.0));
        foods.add(new Food("Majs, kerner, konserves",102.0,3.1,18.5,0.0,2.5,1.1));
        foods.add(new Food("Ribs, rå",63.0,1.3,8.6,0.0,4.2,1.7));
        foods.add(new Food("Sandwich med ribbenssteg, rødkål og agurk, fastfood",251.0,14.3,20.1,1.7,12.3,0.0));
        foods.add(new Food("Krabbekløer, konserves",113.0,22.9,1.0,0.0,0.0,1.8));
        foods.add(new Food("Eddike",20.0,0.4,5.4,0.0,0.0,0.0));
        foods.add(new Food("Makrel, filet, naturel, konserves",222.0,19.0,0.0,0.0,0.0,16.4));
        foods.add(new Food("Grønne ærter, konserves",75.0,5.9,7.7,0.0,6.6,0.8));
        foods.add(new Food("Oliven, sorte, saltede, i olie",353.0,2.2,4.9,0.0,3.8,35.8));
        foods.add(new Food("Laks, atlantisk, opdræt, gravad",183.0,20.2,0.0,0.0,0.0,11.4));
        foods.add(new Food("Blomkål, importeret, rå",31.0,3.1,2.5,0.0,2.7,0.4));
        foods.add(new Food("Småkage, pebernød, industrifremstillet",477.0,5.3,68.8,2.3,19.2,0.0));
        foods.add(new Food("Kartoffel, kogt",86.0,1.7,18.6,1.4,0.1,0.0));
        foods.add(new Food("Rundstykke",297.0,9.6,53.2,0.0,3.5,3.5));
        foods.add(new Food("Tørkage, napoleonshatte",474.0,6.4,55.8,3.0,24.1,0.0));
        foods.add(new Food("Laks, atlantisk, vild, saltet",195.0,8.5,0.0,0.0,0.0,18.2));
        foods.add(new Food("Bolcher, blandede",396.0,0.0,97.5,82.8,0.0,0.0));
        foods.add(new Food("Kage, trøffelprodukt / koldprodukt, industrifremstillet",424.0,3.7,62.1,2.7,17.0,0.0));
        foods.add(new Food("Brisling (sardin), i olie, konserves",205.0,24.6,1.1,0.0,0.0,11.3));
        foods.add(new Food("Lakridskonfekt",399.0,2.3,82.1,50.0,0.6,6.2));
        foods.add(new Food("Grisebryst, stegestykke, uden svær, ca. 2 mm spæk, rå",300.0,15.5,0.0,0.0,0.0,26.8));
        foods.add(new Food("Rullepølse, lammekød, pålæg",358.0,19.0,0.1,0.0,0.0,31.7));
        foods.add(new Food("Oksetalg",860.0,5.0,0.0,0.0,0.0,95.0));
        foods.add(new Food("Smørbart blandingsprodukt, 75% fedt",674.0,0.6,1.1,0.0,75.5,0.0));
        foods.add(new Food("Abrikos, tørret",249.0,3.5,55.1,0.0,9.3,1.7));
        foods.add(new Food("Skærekage, citronkage",429.0,4.6,53.6,1.3,21.3,0.0));
        foods.add(new Food("Bambusskud, rå",41.0,3.6,6.0,0.0,0.7,0.1));
        foods.add(new Food("Kalkunkød, hakket, 5-10% fedt, råt",157.0,20.1,0.0,0.0,8.5,0.0));
        foods.add(new Food("Blodpølse, kogt",310.0,10.7,18.1,0.0,5.0,20.8));
        foods.add(new Food("Grisekød, nakkekam med svær, rå",272.0,16.1,0.0,0.0,0.0,23.4));
        foods.add(new Food("Æg, høne, æggehvide, rå",45.0,10.2,0.8,0.0,0.0,0.0));
        foods.add(new Food("Sej, filet, dybfrost",92.0,20.5,0.3,0.0,0.0,0.9));
        foods.add(new Food("Tyggegummi, med sukker, uspec.",364.0,0.2,88.2,68.0,2.4,0.0));
        foods.add(new Food("Cherimoya, chirimoya, rå",79.0,1.7,15.4,0.0,2.3,0.6));
        foods.add(new Food("Pizza med grønt og frugt, tomat og ost, fastfood",220.0,8.1,26.8,2.4,8.4,0.0));
        foods.add(new Food("Torsk, filet, røget, med skind",80.0,19.0,0.0,0.0,0.0,0.3));
        foods.add(new Food("Kartoffel, konserves",56.0,1.4,10.9,0.0,2.3,0.2));
        foods.add(new Food("Reje, dybvands-, kogt",111.0,24.0,0.9,0.0,0.0,1.1));
        foods.add(new Food("Forårsrulle, friturestegt, fastfood",203.0,6.1,21.4,2.4,9.8,0.0));
        foods.add(new Food("Yoghurt med frugt, 0.4 % fedt",78.0,4.7,13.7,0.0,0.4,0.0));
        foods.add(new Food("Kartoffelmos, pulver uden tørmælk",354.0,8.2,70.6,5.7,14.9,0.5));
        foods.add(new Food("Pølsebrød",254.0,8.7,43.1,0.0,3.6,3.8));
        foods.add(new Food("Oksekød, skank (osso buco), rå",140.0,21.4,0.0,0.0,0.0,6.0));
        foods.add(new Food("Torsk, lever, konserves",524.0,4.6,4.4,0.0,0.0,55.1));
        foods.add(new Food("Jordbærsaft, sød, koncentreret",173.0,0.5,41.0,40.0,0.0,0.5));
        foods.add(new Food("Wienerbrød, uspec.",441.0,4.5,42.7,3.0,3.0,27.3));
        foods.add(new Food("Snaps, krydder-, uspec.",266.0,0.0,10.9,0.0,0.0,0.0));
        foods.add(new Food("Ribbensstegssandwich",277.0,12.3,24.4,1.7,14.1,0.0));
        foods.add(new Food("Hyben pulver, tørret",276.0,4.5,40.0,0.0,43.0,1.5));
        foods.add(new Food("Selleri, rod, rå",34.0,1.8,4.1,0.0,3.9,0.3));
        foods.add(new Food("Knudekål, glaskål, rå",25.0,1.7,2.6,0.0,3.6,0.1));
        foods.add(new Food("Hjerte, høne (kylling), råt",149.0,15.6,0.7,0.0,0.0,9.3));
        foods.add(new Food("Nøddepasta med cacao, smørepålæg",524.0,7.0,54.5,55.0,4.5,30.0));
        foods.add(new Food("Skyr, 0.2 % fedt",71.0,11.0,4.0,0.0,0.0,0.2));
        foods.add(new Food("Skinke, kogt, skiveskåret",110.0,18.0,0.6,1.0,0.0,3.9));
        foods.add(new Food("Sild, Østersø, uspecificeret, rå",122.0,16.2,0.0,0.0,6.4,0.0));
        foods.add(new Food("Sandwich med skinke og ost, salat og dressing, fastfood",226.0,9.3,26.2,1.7,8.9,0.0));
        foods.add(new Food("Creme fraiche 50 %",458.0,1.9,2.0,0.0,0.0,50.0));
        foods.add(new Food("Hvedebrød, toastbrød, fint, industrifremstillet",257.0,7.9,47.0,0.0,3.3,2.8));
        foods.add(new Food("Kartoffelmel",348.0,0.2,85.0,0.0,0.3,0.1));
        foods.add(new Food("Pizza med fisk, tomat og ost, fastfood",257.0,13.8,26.7,2.1,10.0,0.0));
        foods.add(new Food("Sild, rå",187.0,16.5,0.0,0.0,0.0,13.6));
        foods.add(new Food("Majsolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Dressing, creme fraiche",414.0,1.6,9.7,0.0,41.7,0.0));
        foods.add(new Food("Bitter, uspec.",209.0,0.0,0.2,0.2,0.0,0.0));
        foods.add(new Food("Sherry, tør",116.0,0.2,1.3,0.0,0.0,0.0));
        foods.add(new Food("Skinke, røget",230.0,28.4,0.0,0.0,0.0,13.0));
        foods.add(new Food("Æg, høne, blomme, rå",319.0,15.6,1.6,0.0,0.0,28.2));
        foods.add(new Food("Fiskefilet, paneret, stegt, delikatesse",283.0,12.7,22.9,0.0,0.0,15.7));
        foods.add(new Food("Kørvel, rå",21.0,2.2,0.7,0.0,2.0,0.6));
        foods.add(new Food("Rugmel, groft, fuldkornsrugmel",335.0,8.7,61.2,0.0,14.8,2.3));
        foods.add(new Food("Gås, kød, rå",158.0,22.8,0.7,0.0,0.0,7.1));
        foods.add(new Food("Pølse, krakowpølse",281.0,15.2,0.0,0.0,0.0,24.8));
        foods.add(new Food("Grahamsmel, fuldkornshvedemel",339.0,10.7,61.3,0.0,11.6,2.4));
        foods.add(new Food("Flødeskumskage, lagkagesnitte",302.0,3.3,34.4,0.7,16.7,0.0));
        foods.add(new Food("Karse, frisk",27.0,2.8,1.9,0.0,1.6,0.5));
        foods.add(new Food("Æg, høne, røræg, industrifremstillet",117.0,10.8,0.9,0.0,7.9,0.0));
        foods.add(new Food("Kvæde, rå",61.0,0.4,13.4,0.0,1.9,0.1));
        foods.add(new Food("Småkage, cookie (amerikansk type), industrifremstillet",511.0,5.4,60.7,2.9,26.5,0.0));
        foods.add(new Food("Forloren skildpadde, uspec.",109.0,7.9,5.5,0.0,6.2,0.0));
        foods.add(new Food("Kalvekød, magert, råt",122.0,18.6,0.0,0.0,0.0,5.2));
        foods.add(new Food("Kakao, instant, uden mælk, pulver",397.0,4.0,81.3,70.3,5.9,3.8));
        foods.add(new Food("Solbær, syltede",200.0,0.6,46.5,44.0,2.5,0.4));
        foods.add(new Food("Mandarin, konserves",62.0,0.5,13.9,0.0,0.6,0.3));
        foods.add(new Food("Roulade, uspec.",354.0,3.5,61.5,22.0,1.0,9.7));
        foods.add(new Food("Pølse med bacon, ristet, fastfood",315.0,16.3,4.0,0.2,26.3,0.0));
        foods.add(new Food("Tang, kombu, tørret",248.0,7.9,49.5,0.0,5.3,0.5));
        foods.add(new Food("Makaroni, spaghetti, fuldkorns-, rå",355.0,13.1,62.2,0.0,9.6,2.9));
        foods.add(new Food("Kartoffel, gammel (februar - juni), rå",82.0,2.2,16.8,0.0,1.4,0.3));
        foods.add(new Food("Vafler, blokvafler, blandede vafler, vaffelrør",510.0,4.2,64.2,1.5,25.7,0.0));
        foods.add(new Food("Rødspætte, rå",88.0,17.5,0.1,0.0,0.0,1.9));
        foods.add(new Food("Kulmule, rå",70.0,16.3,0.0,0.0,0.0,0.4));
        foods.add(new Food("Peberfrugt, rød, rå",33.0,1.3,5.1,0.0,1.7,0.4));
        foods.add(new Food("Purløg, rå",26.0,2.4,1.5,0.0,2.4,0.6));
        foods.add(new Food("Samsø, 30+",278.0,30.1,1.6,0.0,0.0,17.2));
        foods.add(new Food("Risengryn, grødris, polerede, rå",365.0,6.5,80.0,0.0,0.5,1.3));
        foods.add(new Food("A38, acidophilus tykmælk",68.0,4.9,4.6,0.0,0.0,3.5));
        foods.add(new Food("Asier, syltede",74.0,0.5,16.5,16.0,0.5,0.5));
        foods.add(new Food("Appelsinmarmelade",205.0,0.5,49.0,44.0,0.7,0.3));
        foods.add(new Food("Rødkål, rå",32.0,1.5,4.9,0.0,2.0,0.2));
        foods.add(new Food("Hummer, konserves",90.0,18.7,0.3,0.0,0.0,1.5));
        foods.add(new Food("Ris, parboiled, rå",363.0,7.8,78.1,0.0,0.9,1.2));
        foods.add(new Food("Pizza med kød, tomat og ost, fastfood",258.0,11.9,29.5,1.9,9.8,0.0));
        foods.add(new Food("Müsli med tilsat sukker.",372.0,12.0,60.9,3.5,8.8,6.3));
        foods.add(new Food("Sennep, gul, færdiglavet",165.0,5.7,20.2,14.5,1.7,6.4));
        foods.add(new Food("Sild, marineret",272.0,11.9,19.7,18.0,0.0,16.2));
        foods.add(new Food("Hvid hvede, melmix",344.0,11.9,58.4,9.2,4.2,0.0));
        foods.add(new Food("Vindrue, rå",64.0,0.5,13.7,0.0,1.4,0.4));
        foods.add(new Food("Bladselleri, rå",23.0,0.7,3.9,0.0,1.6,0.2));
        foods.add(new Food("Pizza med salami, tomat og ost, fastfood",313.0,12.8,31.7,1.9,14.5,0.0));
        foods.add(new Food("Grisebov med svær, rå",188.0,18.2,0.0,0.0,0.0,12.9));
        foods.add(new Food("Trekantsandwich med æg, salat og dressing, fastfood",205.0,8.7,20.4,1.7,9.4,0.0));
        foods.add(new Food("Bækforel, filet, røget",204.0,27.7,0.0,0.0,0.0,10.3));
        foods.add(new Food("Grisefilet, røget, pålæg",113.0,19.4,0.2,0.0,0.0,3.8));
        foods.add(new Food("Torsk, rogn, konserves",100.0,13.7,2.8,0.0,0.0,3.7));
        foods.add(new Food("Æg, blomme, tilsat sukker, pasteuriseret, dybfrost",311.0,14.2,11.5,9.7,0.0,23.4));
        foods.add(new Food("Æg, and, rå",180.0,12.8,1.4,0.0,0.0,13.8));
        foods.add(new Food("Grisekød, mellemkam med svær, rå",266.0,18.3,0.1,0.0,0.0,21.7));
        foods.add(new Food("Tun, gulfinnet, rå",114.0,24.4,0.0,0.0,0.0,1.7));
        foods.add(new Food("Sigtemel",355.0,9.6,70.4,0.0,4.4,2.0));
        foods.add(new Food("Søtunge, rå",86.0,18.7,0.1,0.0,0.0,1.1));
        foods.add(new Food("Pistacienød, tørret",568.0,20.6,14.0,0.0,10.3,44.4));
        foods.add(new Food("Hellefisk, gravad",177.0,12.3,0.0,0.0,0.0,14.3));
        foods.add(new Food("Torsk, rogn, røget",147.0,26.8,0.1,0.0,4.2,0.0));
        foods.add(new Food("Sildepostej",341.0,11.1,0.9,0.0,33.1,0.0));
        foods.add(new Food("Tørkage, kokosmakroner",475.0,6.3,48.7,0.0,28.2,0.0));
        foods.add(new Food("Lever, gris, i flødesovs, dybfrost",124.0,11.9,6.9,0.0,5.4,0.0));
        foods.add(new Food("Rundstykke, spansk",285.0,9.4,53.8,0.0,3.4,2.0));
        foods.add(new Food("Småkage, uspec.",502.0,5.3,59.5,3.0,26.1,0.0));
        foods.add(new Food("Skinke, kogt, konserves",112.0,15.7,0.0,0.0,0.0,5.4));
        foods.add(new Food("Pastinak, rå",63.0,2.1,10.2,0.0,4.5,0.5));
        foods.add(new Food("Grapefrugt, rå",44.0,0.9,8.7,0.0,1.4,0.3));
        foods.add(new Food("Danbo, 20+",224.0,28.4,0.8,0.0,0.0,12.4));
        foods.add(new Food("Grapefrugtjuice, konserves, usødet",34.0,0.3,7.9,0.0,0.0,0.1));
        foods.add(new Food("Hindbærsaft, sød, koncentreret",173.0,0.5,41.0,40.0,0.0,0.5));
        foods.add(new Food("Hindbærsaft, sur, koncentreret",35.0,0.5,7.0,0.0,0.0,0.5));
        foods.add(new Food("Sild, røget",215.0,20.5,0.0,0.0,0.0,14.9));
        foods.add(new Food("Kylling, bryst, kød og skind, rå",149.0,21.5,0.1,0.0,0.0,6.9));
        foods.add(new Food("Nektarin, rå",46.0,1.1,9.0,0.0,1.7,0.3));
        foods.add(new Food("Tun i tomat, konserves",118.0,17.2,3.8,0.0,0.0,3.7));
        foods.add(new Food("Bondebrød, industrifremstillet",227.0,6.5,44.0,0.0,4.9,1.3));
        foods.add(new Food("Spegepølse, oksekød",402.0,20.1,0.0,0.0,0.0,36.2));
        foods.add(new Food("Valle",28.0,0.8,5.2,0.0,0.4,0.0));
        foods.add(new Food("Lammekød, bryst, råt",364.0,14.5,0.1,0.0,0.0,34.5));
        foods.add(new Food("Dürumrulle med kebab, salat og dressing, fastfood",225.0,12.6,20.3,2.1,9.9,0.0));
        foods.add(new Food("Persille, rå",48.0,3.7,3.7,0.0,4.1,1.2));
        foods.add(new Food("Guavabær, guajavabær, rå",42.0,0.9,6.3,0.0,4.9,0.5));
        foods.add(new Food("Fårekød, bov, rå",226.0,18.7,0.0,0.0,0.0,17.0));
        foods.add(new Food("Blæksprutte, rå",77.0,16.4,0.7,0.0,0.0,0.9));
        foods.add(new Food("Nougatis",198.0,4.0,22.9,18.0,0.0,10.0));
        foods.add(new Food("Grovbolle",289.0,9.6,49.4,0.0,5.0,4.0));
        foods.add(new Food("Pinjefrø, tørrede",705.0,13.7,6.9,0.0,3.7,68.4));
        foods.add(new Food("Blomkål, dybfrost",27.0,2.0,3.7,0.0,1.0,0.3));
        foods.add(new Food("Grønne ærter, rå",70.0,5.9,7.0,0.0,5.9,0.7));
        foods.add(new Food("Kråse, kylling, rå",113.0,18.2,0.5,0.0,4.2,0.0));
        foods.add(new Food("Vekao",560.0,3.3,59.4,50.0,0.5,33.9));
        foods.add(new Food("Oksekød, hakket, 15-20% fedt, råt",216.0,17.8,0.0,0.0,16.2,0.0));
        foods.add(new Food("Hvedebrød, bolle, fin, industrifremstillet",274.0,8.6,50.6,0.0,2.8,2.8));
        foods.add(new Food("Grisekød, nakkefilet, helt afpudset (Nakkekotelet), rå",181.0,17.4,0.0,0.0,0.0,12.4));
        foods.add(new Food("Øl, Guld-, 5.6 % vol.",48.0,0.4,3.7,0.0,0.0,0.0));
        foods.add(new Food("Bøfsandwich med salat og dressing, fastfood",227.0,9.3,20.0,1.6,11.8,0.0));
        foods.add(new Food("Rosenkål, uspec., rå",53.0,4.5,5.5,0.0,4.1,0.5));
        foods.add(new Food("Ingefær, rod, rå",84.0,1.8,16.7,0.0,1.0,0.8));
        foods.add(new Food("Ispind, limonade",89.0,0.5,16.9,17.0,0.0,2.0));
        foods.add(new Food("Knækbrød, hvede-, grahamstype",374.0,13.0,59.8,0.0,9.5,6.2));
        foods.add(new Food("Pighvarre, rå",91.0,16.4,2.9,0.0,0.0,1.4));
        foods.add(new Food("Pitasandwich med rejer, salat og dressing, fastfood",129.0,5.8,15.1,1.5,4.7,0.0));
        foods.add(new Food("Grisekød, hakket, 15-20% fedt, råt",222.0,17.1,0.0,0.0,0.0,17.2));
        foods.add(new Food("Rugbrød, kun groft rugmel, med hele kerner, detailbageri",212.0,5.1,40.7,7.2,1.2,0.0));
        foods.add(new Food("Hvidvin, sød",95.0,0.2,5.9,0.0,0.0,0.0));
        foods.add(new Food("Voksbønner, rå",35.0,2.1,5.3,0.0,1.8,0.2));
        foods.add(new Food("Wienerbrød, spandauer",407.0,4.7,34.5,3.0,27.2,0.0));
        foods.add(new Food("Tranebær, rå",42.0,0.4,7.6,0.0,4.6,0.1));
        foods.add(new Food("Torsk, filet, paneret, rå",116.0,13.7,12.9,0.0,0.5,0.8));
        foods.add(new Food("Rugbrød, kun groft rugmel, detailbageri",218.0,6.0,40.6,8.2,1.3,0.0));
        foods.add(new Food("Kørvel, hakket, dybfrost",24.0,2.2,1.4,0.0,2.0,0.6));
        foods.add(new Food("Hvedebrød, toastbrød, groft, industrifremstillet",258.0,8.4,45.5,0.0,4.1,3.2));
        foods.add(new Food("Kakao, instant, med mælk, pulver",387.0,5.5,82.0,70.3,0.5,3.4));
        foods.add(new Food("Kakao, pulver",402.0,17.1,17.6,0.0,24.0,21.9));
        foods.add(new Food("Frugtkvark",166.0,6.9,16.2,13.3,0.0,8.2));
        foods.add(new Food("Rasp",356.0,10.7,69.4,0.0,6.9,1.5));
        foods.add(new Food("Soja sauce",57.0,6.9,7.0,0.0,0.0,0.0));
        foods.add(new Food("Dragéprodukt, uspec.",473.0,5.0,73.9,0.0,17.2,0.0));
        foods.add(new Food("Bygmel",341.0,9.2,64.0,0.0,7.6,3.0));
        foods.add(new Food("Mangofrugt, mangoblomme, mangga, rå",67.0,0.5,14.2,0.0,1.9,0.5));
        foods.add(new Food("Asparges, uspec., rå",23.0,1.9,2.2,0.0,1.8,0.3));
        foods.add(new Food("Stenbider, røget",368.0,15.6,0.0,0.0,0.0,34.5));
        foods.add(new Food("Pære, rå",49.0,0.3,11.1,0.0,3.2,0.1));
        foods.add(new Food("Havarti, 30+",290.0,30.1,2.0,0.0,0.0,18.5));
        foods.add(new Food("Yoghurt med müsli",88.0,3.5,11.1,3.8,0.5,3.3));
        foods.add(new Food("Flødeskumskage, jordbærkage",217.0,2.9,32.3,0.7,8.2,0.0));
        foods.add(new Food("Chokolade, mørk",548.0,4.3,59.7,55.9,0.9,31.8));
        foods.add(new Food("Reje, sort tiger-, opdræt, kogt, dybfrost",63.0,14.8,0.0,0.0,0.3,0.0));
        foods.add(new Food("Sukker, stødt melis (saccharose)",406.0,0.0,99.9,99.9,0.0,0.0));
        foods.add(new Food("Ymer naturel",72.0,6.1,4.3,0.0,0.0,3.6));
        foods.add(new Food("Hyldebær, rå",67.0,0.7,11.6,0.0,6.8,0.5));
        foods.add(new Food("Squash, zucchini, rå",19.0,1.4,2.4,0.0,1.0,0.2));
        foods.add(new Food("Cacaomælk (UHT)",70.0,3.6,9.8,5.1,0.5,1.7));
        foods.add(new Food("Champignon, rå",26.0,2.0,2.1,0.0,1.7,0.3));
        foods.add(new Food("Tomatpure, koncentreret",90.0,4.3,14.8,0.0,4.1,0.5));
        foods.add(new Food("Rosenkål, dybfrost",46.0,3.3,4.8,0.0,4.5,0.5));
        foods.add(new Food("Havredrik, tilsat calcium",45.0,0.5,7.1,0.0,1.6,0.0));
        foods.add(new Food("Chesire, 50+",377.0,24.0,1.5,0.0,0.0,31.2));
        foods.add(new Food("Æble, dansk (sort: gråsten), råt",43.0,0.3,8.7,0.0,2.0,0.3));
        foods.add(new Food("Minarine, gennemsnit",367.0,0.5,0.3,0.0,0.0,41.1));
        foods.add(new Food("Likør, kaffe med fløde",330.0,2.8,20.8,20.0,0.0,15.7));
        foods.add(new Food("Medisterpølse, røget",273.0,11.6,3.3,0.5,0.3,24.0));
        foods.add(new Food("Salat, hovedsalat, rå",14.0,1.3,0.8,0.0,1.3,0.4));
        foods.add(new Food("Mangomarmelade",219.0,0.2,53.1,41.0,0.9,0.1));
        foods.add(new Food("Grisekød, bryst med svær, rå",297.0,17.2,0.0,0.0,25.6,0.0));
        foods.add(new Food("Sauce, barbeque",149.0,0.0,35.8,10.0,0.5,0.3));
        foods.add(new Food("Te, drikkeklar, uspec.",0.0,0.0,0.0,0.0,0.0,0.0));
        foods.add(new Food("Skærekage, mazarinkage",491.0,5.2,46.7,1.3,31.2,0.0));
        foods.add(new Food("Oksekød, tykbov (marvpibebov), rå",182.0,19.9,0.0,0.0,0.0,11.4));
        foods.add(new Food("Yderlår, skinkesteg, med svær, rå",181.0,18.5,0.0,0.0,12.0,0.0));
        foods.add(new Food("Skummetmælksost, max. 5+",187.0,43.1,1.9,0.0,0.0,0.9));
        foods.add(new Food("Makrel, rå",186.0,17.8,0.0,0.0,0.0,12.9));
        foods.add(new Food("Hvedebrød, groft, kærnebrød",233.0,6.9,40.3,0.0,8.2,2.6));
        foods.add(new Food("Kartoffelchips, grove",497.0,6.1,57.2,3.6,26.4,0.0));
        foods.add(new Food("Bitter, krabask bitter",226.0,0.0,1.0,1.0,0.0,0.0));
        foods.add(new Food("Cornflakes, frosted",386.0,5.3,87.2,30.0,1.8,0.8));
        foods.add(new Food("Havkat, rå",97.0,16.1,2.0,0.0,0.0,2.7));
        foods.add(new Food("Selleri, blade, rå",34.0,1.8,4.1,0.0,3.9,0.3));
        foods.add(new Food("Øl, skatteklasse 3, uspec.",62.0,0.4,4.6,0.0,0.0,0.0));
        foods.add(new Food("Bitter, sød, uspec.",264.0,0.0,18.0,18.0,0.0,0.0));
        foods.add(new Food("Vindruekerneolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Tunge, kalv, rå",159.0,12.6,0.0,0.0,0.0,12.2));
        foods.add(new Food("Oksekød, tværreb, råt",327.0,15.3,0.0,0.0,0.0,29.9));
        foods.add(new Food("Hornfisk, rå",108.0,19.5,0.0,0.0,3.2,0.0));
        foods.add(new Food("Kiks, grov med frugt eller grønt",436.0,7.8,66.0,6.1,13.7,0.0));
        foods.add(new Food("Tamarind, rå",260.0,2.8,57.4,0.0,5.1,0.6));
        foods.add(new Food("Kaffe, instant, decaffeinated, pulver",358.0,11.6,73.9,0.0,0.0,0.2));
        foods.add(new Food("Kylling, inderfilet, kød, i lage, rå",90.0,20.0,0.0,1.0,0.0,1.0));
        foods.add(new Food("Cultura, syrnet sødmælk (HISTORISK)",63.0,3.5,4.6,0.0,0.0,3.6));
        foods.add(new Food("Snacks, ekstruderet, majsbasis",536.0,9.0,52.3,1.4,32.1,0.0));
        foods.add(new Food("Ris, polerede, rå",359.0,8.4,76.7,0.0,0.7,1.2));
        foods.add(new Food("Jordbærmarmelade",229.0,0.5,54.3,45.0,1.2,0.5));
        foods.add(new Food("Sojadrik, tilsat calcium",45.0,3.3,3.3,0.3,1.9,0.0));
        foods.add(new Food("Fløde 13 % (UHT)",145.0,2.8,4.1,0.0,0.0,13.3));
        foods.add(new Food("Kogt oksebryst, pålæg",273.0,19.0,0.0,0.0,0.0,22.1));
        foods.add(new Food("Wienerbrød, julekage",456.0,5.2,37.6,3.0,31.0,0.0));
        foods.add(new Food("Rødvin, spansk, sød",169.0,0.0,22.8,0.0,0.0,0.0));
        foods.add(new Food("Yoghurt med pære og banan",89.0,3.3,11.8,4.5,0.5,3.2));
        foods.add(new Food("Granatæble, punisk æble, råt",75.0,1.0,16.6,0.0,0.6,0.3));
        foods.add(new Food("Cornflakes, uspec.",379.0,7.5,81.4,5.0,3.1,1.4));
        foods.add(new Food("Flûte, groft, industrifremstillet",262.0,8.1,47.9,0.0,4.0,2.6));
        foods.add(new Food("Skrubbe, filet, paneret, rå, dybfrost",156.0,11.1,22.3,0.0,0.0,2.3));
        foods.add(new Food("Theboller o.l. (bløddejsboller)",299.0,9.1,48.1,3.0,6.6,0.0));
        foods.add(new Food("Knude, rå",71.0,16.4,0.0,0.0,0.0,0.5));
        foods.add(new Food("Bladbede, sølvbede, rå",21.0,1.8,2.1,0.0,1.6,0.2));
        foods.add(new Food("Pasta, kogt",129.0,5.0,24.5,0.0,2.0,0.4));
        foods.add(new Food("Grisekød, uspec., ca. 20 % fedt, råt",241.0,17.9,0.0,0.0,0.0,19.0));
        foods.add(new Food("Likør, Curacao",297.0,0.0,25.5,26.9,0.0,0.0));
        foods.add(new Food("Vinbjergssnegle, rå",85.0,16.0,2.7,0.0,0.0,1.0));
        foods.add(new Food("Mozzarella, 45+",324.0,24.1,1.5,0.0,0.0,25.1));
        foods.add(new Food("Brisling, rå",135.0,10.3,0.0,0.0,0.0,10.5));
        foods.add(new Food("Hestekød, råt",166.0,19.0,0.0,0.0,0.0,10.0));
        foods.add(new Food("Sandwich med kylling, salat og dressing, fastfood",221.0,10.5,28.9,2.0,6.5,0.0));
        foods.add(new Food("Grisebryst, kogestykke med svær, rå",289.0,16.3,0.0,0.0,0.0,25.2));
        foods.add(new Food("Lammekød, rygstykke, råt",288.0,15.8,10.7,0.0,0.0,20.4));
        foods.add(new Food("Poulard, kød og skind, rå",190.0,19.0,3.7,0.0,0.0,11.0));
        foods.add(new Food("Fløde 9 %, syrnet",118.0,6.8,4.4,0.0,0.0,8.2));
        foods.add(new Food("Hindbær, dybfrost, rå",53.0,1.4,6.7,0.0,4.4,1.4));
        foods.add(new Food("Smør, usaltet",730.0,0.2,0.0,0.0,0.0,82.5));
        foods.add(new Food("Kødboller, konserves",126.0,7.1,2.9,0.0,0.0,9.6));
        foods.add(new Food("Skummetmælkspulver",361.0,32.2,54.4,0.0,0.0,1.4));
        foods.add(new Food("Tun i olie, konserves",202.0,24.9,2.1,0.0,0.0,10.4));
        foods.add(new Food("Koriander, blade, tørrede",320.0,21.9,41.5,0.0,10.4,4.8));
        foods.add(new Food("Bækforel, rå",99.0,18.5,0.0,0.0,0.0,2.7));
        foods.add(new Food("Hvidløg, tørret, pulver",349.0,16.8,62.7,0.0,9.9,0.8));
        foods.add(new Food("Radise, rå",11.0,0.8,1.2,0.0,1.3,0.1));
        foods.add(new Food("Kaffebønne, ristet, formalet",399.0,12.4,40.7,0.0,19.8,15.4));
        foods.add(new Food("Tunge, gris, rå",182.0,17.0,6.0,0.0,0.0,10.0));
        foods.add(new Food("Kirsebærsaft, sød, koncentreret",173.0,0.5,41.0,40.0,0.0,0.5));
        foods.add(new Food("Salt kød, pålæg",101.0,19.2,0.0,0.0,0.0,2.6));
        foods.add(new Food("Peberfrugt, grøn, rå",22.0,0.9,3.0,0.0,1.8,0.3));
        foods.add(new Food("Majskolbe, rå",91.0,3.7,13.5,0.0,2.8,1.8));
        foods.add(new Food("Sapote, stor sapodil, rå",145.0,2.1,31.2,0.0,2.6,0.6));
        foods.add(new Food("Hvedebrød, fint, store, industrifremstillet",265.0,8.4,46.6,3.2,3.6,0.0));
        foods.add(new Food("Mørksej, rå",80.0,18.1,0.0,0.0,0.0,0.7));
        foods.add(new Food("Sardin, i tomat, konserves",156.0,16.4,2.8,0.0,0.0,8.8));
        foods.add(new Food("Grahamsbrød",251.0,8.3,44.8,0.0,5.2,2.6));
        foods.add(new Food("Loganbær, frosne",50.0,1.5,7.7,0.0,5.3,0.3));
        foods.add(new Food("Abrikos, konserves, let sukkerlage",69.0,0.5,15.6,14.9,1.5,0.1));
        foods.add(new Food("Helleflynder, røget",106.0,20.6,2.6,0.0,0.0,1.3));
        foods.add(new Food("Müslibar",437.0,6.1,66.7,3.5,15.0,0.0));
        foods.add(new Food("Leverpølse, kalv, pålæg",278.0,13.5,0.0,0.0,25.2,0.0));
        foods.add(new Food("Maribo, 45+",359.0,26.6,1.5,0.0,0.0,28.0));
        foods.add(new Food("Rosin uden kerner",338.0,3.2,74.7,0.0,3.6,1.6));
        foods.add(new Food("Snacks, ekspanderet, kartoffelbasis",490.0,4.6,60.0,3.0,25.1,0.0));
        foods.add(new Food("Kirsebærmarmelade",205.0,0.5,48.5,52.0,1.0,0.5));
        foods.add(new Food("Oksekød, culotte, rå",210.0,19.3,0.0,0.0,0.0,14.9));
        foods.add(new Food("Bitter, jägermeister",293.0,0.0,14.0,14.0,0.0,0.0));
        foods.add(new Food("Havarti, 45+",340.0,24.5,1.7,0.0,0.0,26.8));
        foods.add(new Food("Tomat, dansk, rå",20.0,0.7,2.6,0.0,1.9,0.3));
        foods.add(new Food("Sojabønner, tørrede, rå",411.0,35.8,17.5,0.0,16.6,18.4));
        foods.add(new Food("Jordbær, rå",39.0,0.7,6.9,0.0,1.5,0.6));
        foods.add(new Food("Koriander, frø",341.0,12.4,13.1,0.0,41.9,17.8));
        foods.add(new Food("Madeira",165.0,0.0,10.0,10.0,0.0,0.0));
        foods.add(new Food("Tofu, sojabønneost",76.0,7.8,1.2,0.0,1.0,4.2));
        foods.add(new Food("Morgenmadsprodukt, Havrefras",383.0,13.2,59.4,10.6,7.2,0.0));
        foods.add(new Food("Kartoffel, uspec., rå",78.0,2.0,15.9,0.0,1.4,0.3));
        foods.add(new Food("Pasta, rå",366.0,12.3,71.2,0.0,3.2,1.8));
        foods.add(new Food("Brie, 60+",348.0,18.1,2.0,0.0,0.0,30.3));
        foods.add(new Food("Salat, endivie, bredbladet (escarole), rå",14.0,1.3,0.3,0.0,3.1,0.2));
        foods.add(new Food("Havredrik, ikke beriget",51.0,0.6,9.6,0.2,1.0,0.0));
        foods.add(new Food("Øl, pilsner, 4.4 % vol.",36.0,0.3,2.4,0.0,0.0,0.0));
        foods.add(new Food("Abrikos, konserves, uden tilsat sukker",27.0,0.7,4.9,0.0,1.5,0.2));
        foods.add(new Food("Brune linser, tørrede, rå",340.0,25.1,48.9,11.2,2.0,0.0));
        foods.add(new Food("Hybensaft, sød, koncentreret",235.0,0.0,57.8,57.0,0.0,0.0));
        foods.add(new Food("Asparges, hvide, rå",26.0,2.4,2.5,0.0,1.8,0.3));
        foods.add(new Food("Grøntsagsjuice, pasteuriseret",53.0,2.1,9.2,0.0,1.7,0.4));
        foods.add(new Food("Forårsløg, rå",33.0,1.8,4.7,0.0,2.6,0.2));
        foods.add(new Food("Leverpostej, fedtreduceret",152.0,9.8,12.3,1.0,0.3,6.9));
        foods.add(new Food("Feta, 40+",253.0,19.4,1.9,0.0,0.0,19.2));
        foods.add(new Food("Sagogryn (kartoffelstivelse)",359.0,0.2,87.2,0.0,0.8,0.3));
        foods.add(new Food("Torsk, lever, røget",614.0,5.1,1.0,0.0,0.0,66.6));
        foods.add(new Food("Salt, bordsalt (jodberiget)",0.0,0.0,0.0,0.0,0.0,0.0));
        foods.add(new Food("Småkage, kokoskager, industrifremstillet",458.0,4.1,54.6,5.6,23.4,0.0));
        foods.add(new Food("Avocado, rå",170.0,1.9,9.0,0.0,5.2,13.1));
        foods.add(new Food("Ananas, rå",56.0,0.5,11.8,0.0,1.4,0.4));
        foods.add(new Food("Pizza, dybfrost, uspec.",190.0,8.9,23.8,1.9,6.1,0.0));
        foods.add(new Food("Kikærter, tørrede, rå",368.0,20.4,49.5,0.0,12.2,6.9));
        foods.add(new Food("Yoghurt naturel, 1.5 % fedt",50.0,4.1,4.9,0.0,1.6,0.0));
        foods.add(new Food("Laks, atlantisk, vild, konserves",229.0,20.3,18.2,0.0,0.0,8.2));
        foods.add(new Food("Jagtpølse, pålæg",243.0,13.1,2.5,0.2,20.3,0.0));
        foods.add(new Food("Bayonneskinke, udbenet",192.0,20.8,0.4,0.0,12.0,0.0));
        foods.add(new Food("Chokolade, fløde",544.0,7.1,57.8,49.5,0.4,31.7));
        foods.add(new Food("Kødboller, uspec.",144.0,8.8,6.0,0.0,0.0,9.5));
        foods.add(new Food("Kaviar, uægte (lodderogn)",184.0,9.1,5.7,0.0,0.0,14.0));
        foods.add(new Food("Sild, høstsild, fedsild, rå",246.0,19.3,0.0,0.0,0.0,19.0));
        foods.add(new Food("Danablu 60+",402.0,16.3,1.6,0.0,0.0,37.4));
        foods.add(new Food("Hellefisk, rå",177.0,12.3,0.0,0.0,14.3,0.0));
        foods.add(new Food("Bagepulver",175.0,5.2,37.8,0.0,0.0,0.0));
        foods.add(new Food("Høne, kød, rå",110.0,20.3,1.0,0.0,0.0,2.7));
        foods.add(new Food("Hellefisk, røget",200.0,14.3,0.0,0.0,0.0,16.1));
        foods.add(new Food("Kærnemælk med citronsaft",51.0,3.3,8.5,4.0,0.0,0.5));
        foods.add(new Food("Honningmelon, rå",27.0,0.6,5.4,0.0,0.6,0.2));
        foods.add(new Food("Gulerod, dansk, rå",38.0,0.8,6.3,0.0,2.7,0.4));
        foods.add(new Food("Sandwich med kebab, salat og dressing, fastfood",217.0,8.5,23.7,1.7,9.3,0.0));
        foods.add(new Food("Palmekerneolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Rødbede, konserves, uden tilsat sukker",29.0,0.9,4.9,0.0,2.3,0.1));
        foods.add(new Food("Carambol, bilimbing, stjernefrugt, rå",33.0,1.0,5.2,0.0,2.8,0.3));
        foods.add(new Food("Oksekød, hakket, 10-15% fedt, råt",164.0,19.4,0.0,0.0,0.0,9.7));
        foods.add(new Food("Smørbart blandingsprodukt, 80% fedt",719.0,0.4,0.6,0.0,0.0,80.8));
        foods.add(new Food("Æble, dansk (sort: spartan), råt",51.0,0.2,10.6,0.0,2.4,0.3));
        foods.add(new Food("Brøndkarse, rå",19.0,2.0,1.6,0.0,1.2,0.2));
        foods.add(new Food("Havtorn, rå",74.0,0.7,3.9,6.0,5.0,0.0));
        foods.add(new Food("Grønne bønner, dybfrost",40.0,2.0,5.7,0.0,3.2,0.3));
        foods.add(new Food("Valnød, tørret, rå",683.0,14.3,8.7,0.0,5.6,64.3));
        foods.add(new Food("Bambusskud, konserves, saltet",25.0,2.6,3.2,0.0,0.5,0.1));
        foods.add(new Food("Hvedebrød, solsikkebrød",271.0,9.0,43.5,0.0,5.0,5.1));
        foods.add(new Food("Krabbekløer, rå",113.0,22.9,1.0,0.0,0.0,1.8));
        foods.add(new Food("Modermælk, overgangsmælk (10. dag efter fødslen)",67.0,1.5,6.9,0.0,3.7,0.0));
        foods.add(new Food("Sylte",215.0,13.0,0.2,0.0,0.0,18.2));
        foods.add(new Food("Pitasandwich med falaffel, salat og dressing, fastfood",219.0,6.2,25.7,2.9,9.5,0.0));
        foods.add(new Food("Maribo, 30+",267.0,29.1,1.6,0.0,0.0,16.4));
        foods.add(new Food("Spædbørnsmad, modermælkserstatning, lavt jernindhold, drikkeklar",61.0,1.2,7.5,0.0,3.5,0.0));
        foods.add(new Food("Rugbrød, fuldkorn, importeret",202.0,5.7,36.0,0.0,8.4,1.7));
        foods.add(new Food("Ylette naturel",59.0,6.3,4.6,0.0,0.0,1.8));
        foods.add(new Food("Ørred, hav-, rå",123.0,20.0,3.0,0.0,0.0,3.3));
        foods.add(new Food("Ansjos, marineret, konserves",214.0,13.4,11.0,10.0,0.0,13.0));
        foods.add(new Food("Hvidvin, uden alkohol",19.0,0.2,4.5,0.0,0.0,0.0));
        foods.add(new Food("Melboller, dybfrost",189.0,7.0,9.8,3.2,13.0,0.0));
        foods.add(new Food("Lever, ande, rå",131.0,18.7,3.5,0.0,0.0,4.6));
        foods.add(new Food("Oksekød, uspec., magert, råt",152.0,20.4,0.0,0.0,0.0,7.8));
        foods.add(new Food("Frugtpålæg, figen",319.0,2.5,62.7,0.0,7.5,4.5));
        foods.add(new Food("Lammekød, kølle, uspec., rå",179.0,17.9,0.0,0.0,0.0,12.0));
        foods.add(new Food("Jordbær i sukkerlage, konserves",82.0,0.4,18.6,20.1,0.9,0.4));
        foods.add(new Food("Marcipanprodukt, m/chokolade, uspec.",482.0,4.9,57.0,2.0,25.7,0.0));
        foods.add(new Food("Kransekage o.l., industrifremstillet",420.0,6.0,62.0,2.9,15.3,0.0));
        foods.add(new Food("Hvedebrød, bolle, grov, med fedtrige frø, industrifremstillet",282.0,9.0,45.3,0.0,4.4,5.5));
        foods.add(new Food("Sherry, medium",117.0,0.1,3.4,0.0,0.0,0.0));
        foods.add(new Food("Kongeål (pighaj), rå",160.0,19.1,1.0,0.0,0.0,8.9));
        foods.add(new Food("Hyben, rå",79.0,0.6,15.0,0.0,6.1,0.5));
        foods.add(new Food("Pitasandwich med kylling, salat og dressing, fastfood",166.0,9.8,18.0,1.4,5.7,0.0));
        foods.add(new Food("Sandart, rå",88.0,19.2,0.9,0.0,0.0,0.7));
        foods.add(new Food("Rugsigtemel (udmalingsgrad ca. 70 %)",346.0,6.1,72.4,6.3,1.4,0.0));
        foods.add(new Food("Bønnespirer, mung, rå",34.0,3.0,4.1,0.0,1.8,0.2));
        foods.add(new Food("Kalkun, bryst (filet), kogt/røget, pålæg",105.0,20.7,1.7,1.0,0.0,1.6));
        foods.add(new Food("Lange, filet, rå",81.0,19.5,0.0,0.0,0.0,0.2));
        foods.add(new Food("Oksekød, lårtunge, rå",145.0,21.5,0.0,0.0,0.0,6.5));
        foods.add(new Food("Sild, marineret, i karrysauce",276.0,7.6,11.7,12.0,0.0,22.4));
        foods.add(new Food("Minarine, 30 %, vegetabilsk fedt",315.0,0.5,11.2,0.0,0.0,30.2));
        foods.add(new Food("Æblemost, uspec.",49.0,0.1,11.7,0.0,0.0,0.1));
        foods.add(new Food("Grisefedt, afsmeltet",875.0,0.0,0.0,0.0,0.0,99.0));
        foods.add(new Food("Rødfisk, rå",85.0,18.9,0.0,0.0,0.0,0.9));
        foods.add(new Food("Småkage, brunkage, industrifremstillet",470.0,5.2,70.2,2.4,17.8,0.0));
        foods.add(new Food("Grisekød, kæbe, afpudset, rå",117.0,19.8,0.1,0.0,4.1,0.0));
        foods.add(new Food("Te, blade",230.0,19.6,6.3,55.8,2.0,0.0));
        foods.add(new Food("Remoulade, fedtreduceret, uspec.",224.0,0.8,3.1,10.0,0.5,23.5));
        foods.add(new Food("Kiks, havre, søde, med chokolade",489.0,6.5,61.1,4.6,23.0,0.0));
        foods.add(new Food("Margarine, 80 %, stegning/bagning, animalsk og vegetabilsk fedt",724.0,0.0,0.0,0.0,0.0,81.9));
        foods.add(new Food("Skummetmælk, 0.1 % fedt",35.0,3.6,5.0,0.0,0.1,0.0));
        foods.add(new Food("Flødeskumskage, napoleonskage",357.0,2.8,32.0,0.9,24.2,0.0));
        foods.add(new Food("Hvalkød, råt",112.0,22.3,0.0,0.0,2.4,0.0));
        foods.add(new Food("Kødboller, dybfrost",162.0,10.5,9.0,0.0,0.0,9.4));
        foods.add(new Food("Grønne bønner, konserves",25.0,1.5,2.8,0.0,2.4,0.3));
        foods.add(new Food("Skummetmælk, økologisk",36.0,3.5,4.8,0.0,0.0,0.3));
        foods.add(new Food("Margarine, 80 %, bordbrug, animalsk og vegetabilsk fedt",726.0,0.0,0.0,0.0,0.0,82.1));
        foods.add(new Food("Kastanje, ægte, rå",190.0,2.0,35.8,0.0,6.8,2.7));
        foods.add(new Food("And, brystkød, rå",117.0,19.4,0.0,0.0,0.0,4.3));
        foods.add(new Food("Hvedebrød, trekornsbrød",266.0,9.0,44.0,0.0,5.3,4.2));
        foods.add(new Food("Grønkål, konserves",30.0,2.2,1.6,0.0,3.4,0.9));
        foods.add(new Food("Fasan, kød, rå",122.0,22.2,0.0,0.0,0.0,3.6));
        foods.add(new Food("Bambusskud, konserves, usaltet",25.0,2.6,3.2,0.0,0.5,0.1));
        foods.add(new Food("Æg, høne, burhøns, rå",136.0,12.0,1.1,0.0,9.4,0.0));
        foods.add(new Food("Øl, mørkt hvidtøl",45.0,0.3,8.6,0.0,0.0,0.0));
        foods.add(new Food("Jordbær, dybfrost, usukrede",41.0,0.4,7.9,0.0,2.1,0.4));
        foods.add(new Food("Burger med medium bøf, salat og dressing, fastfood",221.0,10.4,19.3,1.6,11.0,0.0));
        foods.add(new Food("Flûte, fint, industrifremstillet",259.0,8.2,50.8,0.0,3.0,1.2));
        foods.add(new Food("Dild, rå",48.0,3.5,4.9,0.0,2.1,1.1));
        foods.add(new Food("Majsstivelse",367.0,0.4,88.3,0.0,0.5,0.6));
        foods.add(new Food("Sveske, rå",232.0,2.7,54.6,0.0,7.6,0.7));
        foods.add(new Food("Kinakål, pe-tsai, rå",17.0,1.3,2.0,0.0,1.2,0.2));
        foods.add(new Food("Maniok, kassava, rå",159.0,1.4,36.3,0.0,1.8,0.3));
        foods.add(new Food("Torsk, levertran",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Gelatine",354.0,86.9,0.0,0.0,0.0,0.1));
        foods.add(new Food("Kødpølse, pålæg",266.0,11.3,2.3,0.5,0.3,23.8));
        foods.add(new Food("Hvilling, rå",79.0,18.2,0.0,0.0,0.0,0.6));
        foods.add(new Food("Kuller, paneret, stegt",198.0,25.0,1.8,0.0,0.2,10.0));
        foods.add(new Food("Æble, tørret",256.0,0.9,57.2,0.0,8.7,0.3));
        foods.add(new Food("Rabarber, dybfrost",26.0,0.9,3.0,0.0,3.8,0.3));
        foods.add(new Food("Kiks, Marie",438.0,7.0,75.9,20.0,2.6,10.6));
        foods.add(new Food("Croissant",419.0,7.7,41.0,2.3,24.1,0.0));
        foods.add(new Food("Banan, rå",95.0,1.1,21.0,0.0,1.6,0.2));
        foods.add(new Food("Æg, høne, pasteuriseret",131.0,11.4,2.0,0.0,0.0,8.6));
        foods.add(new Food("Bouillon, oksekød, koncentreret, terning",162.0,17.0,15.8,0.0,3.2,0.0));
        foods.add(new Food("A38, acidophilus tykmælk, med citronsaft (HISTORISK)",75.0,4.9,6.5,4.0,0.0,3.5));
        foods.add(new Food("Æble, importeret, råt",58.0,0.3,11.3,0.0,2.5,0.7));
        foods.add(new Food("Peberfrugt, konserves",19.0,0.8,2.7,0.0,1.2,0.3));
        foods.add(new Food("Salat, mayonnaise-, italiensk",303.0,1.9,7.2,1.6,29.7,0.0));
        foods.add(new Food("Spædbørnsmad, modermælkserstatning, højt jernindhold, pulver",505.0,11.3,56.9,0.0,25.9,0.0));
        foods.add(new Food("Ribsgele",241.0,0.2,59.0,57.0,0.0,0.1));
        foods.add(new Food("Dressing, creme fraiche, kalorielet",210.0,1.3,9.3,0.0,18.9,0.0));
        foods.add(new Food("Likør, uspec.",269.0,0.0,29.3,29.0,0.0,0.0));
        foods.add(new Food("Savoykål, rå",27.0,2.0,3.0,0.0,3.1,0.1));
        foods.add(new Food("Burger med bøf, bacon og ost, salat og dressing, fastfood",232.0,11.1,17.5,2.0,12.6,0.0));
        foods.add(new Food("Æg, høne, skrabehøns, rå",139.0,12.3,1.1,0.0,9.5,0.0));
        foods.add(new Food("Tun i vand, konserves",108.0,23.9,0.0,0.0,0.0,1.2));
        foods.add(new Food("Kokosfedt",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Dressing, thousand island",370.0,1.1,12.7,2.0,35.1,0.0));
        foods.add(new Food("Rødbede, rå",53.0,1.7,9.6,0.0,2.3,0.3));
        foods.add(new Food("Elbo, 40+",319.0,26.6,1.5,0.0,0.0,23.4));
        foods.add(new Food("Pistacienød, tørristet",583.0,21.3,13.5,0.0,10.3,46.0));
        foods.add(new Food("Hvedebrød med fedtrige frø, store, industrifremstillet",268.0,9.6,39.1,0.0,5.3,6.4));
        foods.add(new Food("Laks, atlantisk, opdræt, koldrøget",179.0,20.6,0.0,0.0,10.8,0.0));
        foods.add(new Food("Paranød, tørret, rå",680.0,15.0,6.1,0.0,5.3,65.0));
        foods.add(new Food("Wienerpølse, konserves",199.0,10.6,1.4,0.0,0.2,17.0));
        foods.add(new Food("Drueagurk (sylteagurk), rå",17.0,1.2,2.1,1.3,0.1,0.0));
        foods.add(new Food("Oksekød, tyndbov + mellembov, rå",204.0,19.2,0.0,0.0,0.0,14.2));
        foods.add(new Food("Roastbeef, pålæg",120.0,21.7,0.3,0.0,3.5,0.0));
        foods.add(new Food("Vand, drikke-, vejl. værdier",0.0,0.0,0.0,0.0,0.0,0.0));
        foods.add(new Food("Læskedrik (light), koncentreret (1+10), uden tilsat sukker, uspec.",6.0,0.0,2.0,0.0,0.0,0.0));
        foods.add(new Food("Appelsinjuice, konserves",43.0,0.6,9.7,0.0,0.2,0.1));
        foods.add(new Food("Saccharin",366.0,0.9,89.1,0.0,0.0,0.0));
        foods.add(new Food("Oksekød, yderlår, råt",146.0,20.5,0.0,0.0,0.0,7.1));
        foods.add(new Food("Sild, marineret, kryddersild",228.0,12.4,10.7,0.0,15.2,0.0));
        foods.add(new Food("Pastiller, med sukker, uspec.",377.0,4.4,84.9,0.0,1.6,0.0));
        foods.add(new Food("Småkage, finsk brød",536.0,5.6,56.9,2.0,31.2,0.0));
        foods.add(new Food("Brombær, dybfrost, usukrede",66.0,1.2,13.0,0.0,2.7,0.4));
        foods.add(new Food("Helleflynder, rå",106.0,20.6,2.6,0.0,0.0,1.3));
        foods.add(new Food("Havarti, 60+",422.0,19.5,2.1,0.0,0.0,38.1));
        foods.add(new Food("Mineralvand, dansk vand o.lign.",4.0,0.0,1.0,0.0,0.0,0.0));
        foods.add(new Food("Lakrids, uspec.",364.0,3.7,83.3,48.0,0.0,1.2));
        foods.add(new Food("Campari",179.0,0.0,10.0,10.0,0.0,0.0));
        foods.add(new Food("Bagegær, presset, rå",114.0,7.7,12.7,0.0,6.2,1.9));
        foods.add(new Food("Sperling, kød, rå",80.0,17.6,0.0,0.0,0.0,1.0));
        foods.add(new Food("Tomatketchup, stærk",104.0,2.0,23.2,21.0,0.8,0.0));
        foods.add(new Food("Ispind, vanille",186.0,4.0,20.0,19.0,0.0,10.0));
        foods.add(new Food("Dressing, olie-eddike, kalorielet",47.0,0.3,8.9,0.0,1.1,0.0));
        foods.add(new Food("Makrel i tomat, konserves",155.0,11.7,3.9,0.0,0.0,10.3));
        foods.add(new Food("Kaffe, instant, drikkeklar",3.0,0.1,0.7,0.0,0.0,0.0));
        foods.add(new Food("Fløde 9 %",110.0,3.3,4.2,0.0,0.0,9.0));
        foods.add(new Food("Ost, blå- og hvidskimmel, 70+",443.0,12.8,0.9,0.0,0.0,44.1));
        foods.add(new Food("Lammekød, kølle, med fedt, rå",199.0,18.4,0.0,0.0,0.0,14.1));
        foods.add(new Food("Marcipan",477.0,11.0,45.1,40.0,3.0,26.5));
        foods.add(new Food("Småkage, hvede, traditionelle danske, industrifremstillet",520.0,4.9,62.6,2.0,27.1,0.0));
        foods.add(new Food("Whisky",250.0,0.0,0.1,0.0,0.0,0.0));
        foods.add(new Food("Kylling, kød og skind, grillstegt",194.0,24.2,0.0,0.0,0.0,10.8));
        foods.add(new Food("Brombær, syltede",200.0,0.3,48.1,44.0,0.9,0.2));
        foods.add(new Food("Mælkeis, uspec.",155.0,4.0,27.7,21.5,0.0,3.0));
        foods.add(new Food("Hvedemel",352.0,9.6,70.9,0.0,3.7,1.6));
        foods.add(new Food("Flûte med fedtrige frø, industrifremstillet",273.0,8.5,48.5,0.0,4.5,3.3));
        foods.add(new Food("Ispind, jordbær",158.0,4.0,24.0,17.0,0.0,5.0));
        foods.add(new Food("Nyre, gris, rå",99.0,16.0,1.7,0.0,0.0,3.0));
        foods.add(new Food("Løg, rå",45.0,1.6,8.0,0.0,1.9,0.3));
        foods.add(new Food("Kirsebær, syltede",146.0,1.0,34.4,31.0,0.6,0.1));
        foods.add(new Food("Müsli, uspec.",407.0,9.4,62.6,3.5,7.2,11.1));
        foods.add(new Food("Reje, dybvands, kogt, i lage",111.0,24.0,0.9,0.0,0.0,1.1));
        foods.add(new Food("Trekantsandwich med kylling og bacon, salat og dressing, fastfood",207.0,11.3,19.9,1.7,8.7,0.0));
        foods.add(new Food("Grisekød, skinkeklump, helt afpudset, rå",101.0,20.8,0.0,0.0,1.9,0.0));
        foods.add(new Food("Rødtunge, rå",72.0,16.3,0.0,0.0,0.0,0.7));
        foods.add(new Food("Rugbrød, lyst",207.0,5.4,39.4,0.0,6.9,1.2));
        foods.add(new Food("Rugbrød, revet, med brunt sukker, uspec.",352.0,6.1,71.2,40.0,9.8,2.0));
        foods.add(new Food("Wienerpølse",265.0,12.5,3.4,0.5,0.2,22.6));
        foods.add(new Food("Knækbrød, fladbrød",364.0,9.1,67.1,0.0,15.8,2.3));
        foods.add(new Food("Hvedekimolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Cashewnød, tørristet",598.0,15.3,26.9,0.0,3.0,46.4));
        foods.add(new Food("Lammebrissel, rå",131.0,15.3,0.0,0.0,0.0,7.8));
        foods.add(new Food("Fiskefrikadelle",155.0,13.7,9.8,0.0,0.0,6.7));
        foods.add(new Food("Rugbrød, med sigtet mel (soft) og fedtrige frø, industrifremstillet",229.0,6.4,34.6,0.0,7.7,5.2));
        foods.add(new Food("Spegesild",261.0,13.1,0.0,0.0,0.0,23.5));
        foods.add(new Food("Vanilleis",198.0,4.0,22.9,16.0,0.0,10.0));
        foods.add(new Food("Løg, hakkede, dybfrost",28.0,0.8,5.0,0.0,1.8,0.1));
        foods.add(new Food("Karameller, blandede",483.0,2.1,71.4,48.0,0.0,20.9));
        foods.add(new Food("Margarine, 80 %, bordbrug, vegetabilsk fedt",718.0,0.8,0.6,0.0,0.0,80.6));
        foods.add(new Food("Levertran (Sej)",884.0,0.0,0.1,0.0,0.0,99.9));
        foods.add(new Food("Kartoffel, ny (maj til september), rå",77.0,1.8,15.8,0.0,1.3,0.3));
        foods.add(new Food("Bacon, kogestykke, rå",375.0,15.0,1.1,0.0,0.0,35.0));
        foods.add(new Food("Kiks, havre, søde",469.0,6.9,62.9,4.9,19.6,0.0));
        foods.add(new Food("Sojaolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Camembert, 30+",199.0,25.0,0.5,0.0,0.0,11.0));
        foods.add(new Food("Salat, Iceberg, rå",16.0,1.0,2.2,0.0,1.1,0.1));
        foods.add(new Food("Vallepulver, tørret",365.0,12.9,74.7,0.0,1.1,0.0));
        foods.add(new Food("Hvedekim, rå",381.0,28.1,36.9,0.0,12.3,9.6));
        foods.add(new Food("Ising, rå",76.0,16.6,0.0,0.0,0.0,1.0));
        foods.add(new Food("Voksbønner, konserves",26.0,1.4,3.7,0.0,1.5,0.3));
        foods.add(new Food("Dild, tørret",321.0,20.0,43.9,0.0,11.9,4.4));
        foods.add(new Food("Aubergine, rå",20.0,0.9,2.6,0.0,2.4,0.1));
        foods.add(new Food("Brie, 45+",283.0,20.8,1.3,0.0,0.0,22.0));
        foods.add(new Food("Kiks, hvede, sød, med tyk chokolade",504.0,6.4,61.4,3.2,24.9,0.0));
        foods.add(new Food("Majroe, rå",26.0,0.6,4.7,0.0,2.0,0.1));
        foods.add(new Food("Dressing, olie-eddike",346.0,0.6,9.8,0.0,34.3,0.0));
        foods.add(new Food("Torsk, filet, stegt",121.0,18.7,0.1,0.0,0.0,5.0));
        foods.add(new Food("Hjerte, gris, råt",103.0,16.1,0.0,0.0,0.0,4.3));
        foods.add(new Food("Hvide bønner, tørrede, rå",324.0,21.3,43.9,0.0,18.4,2.7));
        foods.add(new Food("Oksekød, hakket, 5-10% fedt, råt",163.0,19.5,0.0,0.0,9.5,0.0));
        foods.add(new Food("Hvidløg, rå",160.0,6.4,30.9,0.0,2.1,0.5));
        foods.add(new Food("Burger med bøf og bacon, salat og dressing, fastfood",254.0,11.7,19.2,1.6,14.2,0.0));
        foods.add(new Food("Melbanan, grønsagsbanan, plantain, rå",133.0,1.3,29.6,0.0,2.3,0.4));
        foods.add(new Food("Torsk, lever, rå",614.0,5.1,1.0,0.0,0.0,66.6));
        foods.add(new Food("Hvedebrød, bolle, grov, industrifremstillet",249.0,8.2,43.5,5.9,2.7,0.0));
        foods.add(new Food("Ørred, regnbue-, rå",139.0,18.3,0.0,0.0,0.0,7.3));
        foods.add(new Food("Yoghurt, sødmælk, med frugt",89.0,3.3,11.8,4.5,0.5,3.2));
        foods.add(new Food("Kiwimarmelade",228.0,0.4,54.7,44.0,1.3,0.2));
        foods.add(new Food("Småkage, havreflager, industrifremstillet",517.0,4.5,62.4,2.6,27.0,0.0));
        foods.add(new Food("Artiskok, rå",52.0,3.3,8.1,2.4,0.2,0.0));
        foods.add(new Food("Fennikel, knold, rå",23.0,1.4,2.9,0.0,2.0,0.2));
        foods.add(new Food("Kalv og flæsk, hakket, 15-20% fedt, råt",216.0,17.6,0.0,0.0,0.0,16.4));
        foods.add(new Food("Dressing, italiensk",295.0,0.4,10.4,0.0,28.4,0.0));
        foods.add(new Food("Kefir (HISTORISK)",63.0,3.5,4.6,0.0,0.0,3.6));
        foods.add(new Food("Millionbøf, uspec.",126.0,8.1,4.9,0.0,8.3,0.0));
        foods.add(new Food("Havreklid, dansk",368.0,17.3,49.6,15.4,7.0,0.0));
        foods.add(new Food("Tungepølse, pålæg",147.0,9.8,4.8,0.0,10.0,0.0));
        foods.add(new Food("Bomuldsfrøolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Rugbrød, med sigtet mel (soft) og fedtrige frø, detailbageri",251.0,7.9,32.6,7.2,8.0,0.0));
        foods.add(new Food("Modermælk, colostrum",58.0,2.0,6.6,0.0,2.6,0.0));
        foods.add(new Food("Appelsin, rå",51.0,0.9,9.5,0.0,2.0,0.6));
        foods.add(new Food("Sesamolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Rødkål, konserves, uden tilsat sukker",20.0,0.5,2.9,0.0,2.6,0.1));
        foods.add(new Food("Hvedebrød, fint, store, detailbageri",277.0,8.2,49.3,3.1,3.8,0.0));
        foods.add(new Food("Oksekød, uspec., fedt, råt",318.0,16.4,0.2,0.0,0.0,28.4));
        foods.add(new Food("Sandwich med frikadelle, salat og dressing, fastfood",219.0,8.8,23.9,1.7,9.4,0.0));
        foods.add(new Food("Kartoffelmos, pulver med tørmælk",336.0,10.9,62.5,5.7,14.9,1.1));
        foods.add(new Food("Smør, saltet",728.0,0.7,1.2,0.0,0.0,81.5));
        foods.add(new Food("Knækbrød, rug-, fint",348.0,10.7,62.7,0.0,14.5,2.0));
        foods.add(new Food("Hyldebærsaft, sur, koncentreret",31.0,0.5,6.0,0.0,0.0,0.5));
        foods.add(new Food("Hajmalle, rå, dybfrost",70.0,14.6,0.0,0.0,0.0,1.3));
        foods.add(new Food("Emmentaler, 45+",375.0,28.1,1.6,0.0,0.0,29.1));
        foods.add(new Food("Linsespirer, rå",125.0,9.0,19.1,0.0,3.1,0.6));
        foods.add(new Food("Smelteost, 30+",255.0,26.4,1.0,0.0,0.0,16.5));
        foods.add(new Food("Mozzarella, 30+",278.0,28.9,1.6,0.0,0.0,17.7));
        foods.add(new Food("Lakrids, sød",350.0,6.8,74.9,41.0,0.0,2.0));
        foods.add(new Food("Wienerbrød, smørkrans",467.0,4.3,41.3,3.0,31.0,0.0));
        foods.add(new Food("Hvidvin, medium",78.0,0.1,2.3,0.0,0.0,0.0));
        foods.add(new Food("Boghvedegryn, rå",360.0,7.0,77.0,0.0,2.3,1.4));
        foods.add(new Food("Persillerod, rå",55.0,2.3,8.0,0.0,4.3,0.6));
        foods.add(new Food("Sandwich med fisk, salat og dressing, fastfood",204.0,9.6,24.9,1.8,6.8,0.0));
        foods.add(new Food("Æg, høne, langæg, kogt, dybfrost",128.0,12.0,2.2,0.0,0.0,8.0));
        foods.add(new Food("Letmælk, konventionel (ikke-økologisk)",48.0,3.5,4.9,0.0,0.0,1.6));
        foods.add(new Food("Hamburgerryg, kogt",118.0,18.5,0.9,0.5,0.0,4.5));
        foods.add(new Food("Hot dog, almindelig, fastfood",256.0,8.5,20.8,1.6,15.1,0.0));
        foods.add(new Food("Quinoa, rå",351.0,13.5,54.8,12.1,5.7,0.0));
        foods.add(new Food("Grisekød i karrysovs, dybfrost",119.0,8.5,2.0,0.0,8.6,0.0));
        foods.add(new Food("Hvid hvede brød, groft, store, industrifremstillet",225.0,7.9,39.4,7.8,1.7,0.0));
        foods.add(new Food("Yderlår, skinkeculotte, ca. 2 mm spæk, rå",162.0,19.0,0.0,0.0,9.6,0.0));
        foods.add(new Food("Skinke, inderlår, helt afpudset, rå",107.0,21.4,0.1,0.0,0.0,2.2));
        foods.add(new Food("Bouillon, oksekød, terning, spiseklar",7.0,1.1,0.1,0.0,0.2,0.0));
        foods.add(new Food("Musling, kogt",166.0,23.8,7.4,0.0,0.0,4.5));
        foods.add(new Food("Hvid hvede bolle, grov, industrifremstillet",234.0,8.2,40.7,7.4,2.0,0.0));
        foods.add(new Food("Kvark, 5+",58.0,9.1,5.0,0.0,0.0,0.3));
        foods.add(new Food("Linser, tørrede, rå",350.0,26.2,50.5,0.0,8.7,2.5));
        foods.add(new Food("Kanin, kød, rå",118.0,20.4,0.0,0.0,0.0,4.0));
        foods.add(new Food("Koriander, blade, friske",22.0,2.1,0.9,0.0,2.8,0.5));
        foods.add(new Food("Kødrand, dybfrost",217.0,15.8,3.9,0.0,15.5,0.0));
        foods.add(new Food("Sirup",312.0,0.3,76.6,0.0,0.0,0.0));
        foods.add(new Food("Vermouth, tør",117.0,0.1,5.2,0.0,0.0,0.0));
        foods.add(new Food("Feta, 50+",301.0,17.8,1.9,0.0,0.0,25.2));
        foods.add(new Food("Fløde 38 %, piskefløde",355.0,2.1,2.5,0.0,0.0,38.0));
        foods.add(new Food("Asparges, konserves",16.0,1.6,1.5,1.5,0.1,0.0));
        foods.add(new Food("Passionsfrugt, grenadil, rå",104.0,2.8,15.2,0.0,8.0,1.8));
        foods.add(new Food("Rom",275.0,0.0,0.0,0.0,0.0,0.0));
        foods.add(new Food("Cognac",222.0,0.0,0.0,0.0,0.0,0.0));
        foods.add(new Food("Jomfruhummer, rå",94.0,19.6,0.7,0.0,0.0,1.3));
        foods.add(new Food("Tahin, lys",611.0,17.0,8.8,9.3,53.8,0.0));
        foods.add(new Food("Cacaoskummetmælk (UHT)",60.0,3.6,10.0,4.8,0.5,0.5));
        foods.add(new Food("Hvedebrød, groft, med fedtrige frø, stort, detailbageri",282.0,9.3,44.3,3.8,6.1,0.0));
        foods.add(new Food("Æblegele",246.0,0.5,59.0,56.0,0.0,0.5));
        foods.add(new Food("Kanel, stang",243.0,4.0,28.1,0.0,53.1,1.2));
        foods.add(new Food("Kærnemælk",37.0,3.4,4.8,0.0,0.0,0.5));
        foods.add(new Food("Tørkage, kransekage",483.0,9.2,51.2,6.8,25.0,0.0));
        foods.add(new Food("Salat, mayonnaise-, karry-",370.0,1.7,8.3,0.7,37.1,0.0));
        foods.add(new Food("Creme fraiche 9 %",109.0,3.4,3.9,0.0,0.0,9.0));
        foods.add(new Food("Paradisæble, råt",84.0,0.4,19.3,0.0,0.6,0.3));
        foods.add(new Food("Morgenmadsprodukt, Rice Krispies",377.0,6.0,82.7,7.0,1.4,1.5));
        foods.add(new Food("Æg, høne, økologisk drift, rå",138.0,12.3,1.3,0.0,9.3,0.0));
        foods.add(new Food("Dressing, fransk",463.0,0.8,15.6,5.0,0.0,44.8));
        foods.add(new Food("Æg, høne, tørret",562.0,50.7,4.2,0.0,0.0,38.4));
        foods.add(new Food("Skorzonerrod, rå",84.0,3.3,15.3,0.0,3.3,0.2));
        foods.add(new Food("Reje, sort tiger-, opdræt, rå, dybfrost",68.0,15.8,0.0,0.0,0.0,0.5));
        foods.add(new Food("Esrom, 45+",329.0,24.2,1.5,0.0,0.0,25.7));
        foods.add(new Food("Solsikkeolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Lammekød, kølle, afpudset, rå",128.0,19.6,0.0,0.0,0.0,5.5));
        foods.add(new Food("Gåsefedt",883.0,0.0,0.0,0.0,0.0,99.8));
        foods.add(new Food("Hørfrø, rå",497.0,25.0,24.1,0.0,12.1,31.0));
        foods.add(new Food("Fløde 50 %",461.0,1.9,2.7,0.0,0.0,50.0));
        foods.add(new Food("Rugmel, halvsigtet (udmalingsgrad ca. 85 %)",340.0,7.8,66.2,10.5,1.8,0.0));
        foods.add(new Food("Torsk, filet, rå",77.0,17.6,0.0,0.0,0.0,0.6));
        foods.add(new Food("Sødmælk, økologisk",64.0,3.4,5.0,0.0,0.0,3.5));
        foods.add(new Food("Ræddike, rå",22.0,1.0,3.9,0.0,0.7,0.1));
        foods.add(new Food("Ost, fast, 30+, alle typer",262.0,28.7,1.6,0.0,0.0,16.0));
        foods.add(new Food("Hvedebrød, bolle, italiensk type, detailbageri",260.0,9.0,46.8,3.1,2.7,0.0));
        foods.add(new Food("Burger med stor bøf, salat og dressing, fastfood",231.0,12.7,15.0,1.4,13.1,0.0));
        foods.add(new Food("Chokolade, koge-",560.0,5.7,57.2,50.0,0.9,34.3));
        foods.add(new Food("Rosenkål, vinter (januar til april), rå",51.0,4.4,5.1,0.0,4.1,0.5));
        foods.add(new Food("Salami",503.0,13.9,2.8,0.0,0.0,49.2));
        foods.add(new Food("And, kød og skind, rå",395.0,11.5,0.0,0.0,0.0,39.3));
        foods.add(new Food("Fransk hot dog, fastfood",284.0,10.2,25.9,1.6,15.2,0.0));
        foods.add(new Food("Mineralvand, sodavand, tilsat sukker, uspec.",41.0,0.0,10.0,9.8,0.0,0.0));
        foods.add(new Food("Sødmælk, konventionel (ikke-økologisk)",63.0,3.4,4.7,0.0,0.0,3.5));
        foods.add(new Food("Fersken, tørret",336.0,4.9,68.9,0.0,14.3,1.0));
        foods.add(new Food("Cheddar, 50+",402.0,26.2,1.5,0.0,0.0,33.1));
        foods.add(new Food("Hørfrøolie",884.0,0.0,0.0,0.0,0.0,100.0));
        foods.add(new Food("Squash, alle typer, rå",17.0,1.7,1.4,0.0,1.2,0.2));
        foods.add(new Food("Skrubbe, rå",87.0,17.9,0.0,0.0,0.0,1.6));
        foods.add(new Food("Fløde 13 %",149.0,2.9,4.6,0.0,0.0,13.5));
        foods.add(new Food("Kartoffel, chips (franske kartofler)",531.0,5.4,54.3,0.0,3.4,31.9));
        foods.add(new Food("Mandeldrik, ikke beriget",47.0,0.7,5.8,0.3,2.2,0.0));
        foods.add(new Food("Kartoffel, chips, fedtreducerede",488.0,5.0,63.5,0.0,3.4,23.0));
        foods.add(new Food("Tunge, kalv, saltet",156.0,13.1,0.0,0.0,0.0,11.6));
        foods.add(new Food("Æg, høne, æggehvide, pasteuriseret",44.0,9.0,1.9,0.0,0.0,0.0));
        foods.add(new Food("Lammekød, uspec., råt",326.0,13.9,0.0,0.0,0.0,30.5));
        foods.add(new Food("Brune bønner, tørrede, rå",314.0,18.9,45.6,0.0,17.8,2.0));
        foods.add(new Food("Æg, høne, æggehvide, tørret",362.0,82.3,6.8,0.0,0.0,0.0));
        foods.add(new Food("Kylling, bryst (filet), kogt, pålæg",111.0,20.4,0.8,1.0,0.0,2.8));
        foods.add(new Food("Spinat, konserves",22.0,2.7,0.7,0.0,2.0,0.5));
        foods.add(new Food("Sødmælk (UHT)",63.0,3.4,4.7,0.0,0.0,3.5));
        foods.add(new Food("Ål, røget",325.0,19.4,0.2,0.0,0.0,27.8));
        foods.add(new Food("Due, vildt, kød og skind, rå",284.0,18.2,0.0,0.0,0.0,23.8));
        foods.add(new Food("Remoulade, uspec.",375.0,1.1,10.7,12.0,0.5,36.8));
        foods.add(new Food("Tomatpure",44.0,1.6,8.6,0.0,0.4,0.2));
        foods.add(new Food("Fiskepinde, panerede, dybfrost",188.0,12.8,15.1,0.0,0.0,8.4));
        foods.add(new Food("Tunge, gris, saltet",181.0,17.0,5.9,0.0,0.0,10.0));
        foods.add(new Food("Batat, sød kartoffel, rå",72.0,1.3,14.5,0.0,2.7,0.3));
        foods.add(new Food("Peanuts, (jordnød, ristet og saltet)",625.0,24.6,5.7,0.0,7.7,53.3));
        foods.add(new Food("Kalvekød, middelfedt, råt",184.0,20.0,0.6,0.0,0.0,11.3));
        foods.add(new Food("Frugtsaft, blandet, sødet, koncentreret",177.0,0.2,43.4,41.4,0.0,0.0));
        foods.add(new Food("Musling, konserves",103.0,17.0,1.9,0.0,0.0,3.0));
        foods.add(new Food("Skærekage, chokoladekage/-pie",432.0,5.3,48.8,1.3,23.5,0.0));
        foods.add(new Food("Sesamfrø, afskallede",600.0,26.4,0.0,0.0,11.6,51.0));
        foods.add(new Food("Oksekød, tyndstegsfilet med fedtkant, rå",217.0,20.3,0.0,0.0,0.0,15.2));
        foods.add(new Food("Tang, agar, tørret",303.0,2.3,69.3,0.0,5.0,0.3));
        foods.add(new Food("Sherry, sød",135.0,0.3,6.5,0.0,0.0,0.0));
        foods.add(new Food("Lucerne, alfalfa, frø",407.0,35.1,33.9,0.0,7.9,12.6));
        foods.add(new Food("Vingummi",354.0,6.6,78.6,40.6,0.0,0.9));
        foods.add(new Food("Rødbede, konserves",61.0,1.0,12.4,3.4,2.0,0.3));
        foods.add(new Food("Æg, høne, blomme, pasteuriseret",243.0,13.0,2.6,0.0,20.3,0.0));
        foods.add(new Food("Kiks til ost, Cream Crackers",445.0,9.4,67.7,1.6,3.0,13.9));
        foods.add(new Food("Sojadrik, kommercielt tilberedt, ikke beriget",55.0,3.4,5.0,2.2,0.3,2.3));
        foods.add(new Food("Danablu 50+",346.0,20.5,1.1,0.0,0.0,29.5));
        foods.add(new Food("Lever, kylling, rå",116.0,19.1,0.4,0.0,0.0,4.2));
        foods.add(new Food("Tomatjuice, konserves",19.0,0.8,3.3,0.0,0.9,0.1));
        foods.add(new Food("Fasan, kød og skind, rå",170.0,21.7,0.0,0.0,0.0,9.3));
        foods.add(new Food("Bønnespirer, adzuki, rå",136.0,10.0,18.8,0.0,5.2,1.0));
        foods.add(new Food("Letmælkskefir",48.0,3.5,4.9,0.0,0.0,1.6));
        foods.add(new Food("Muskatnød, hel",501.0,6.0,28.6,0.0,20.8,36.3));
        foods.add(new Food("Pølsemix med pommes frites, fastfood",322.0,8.0,26.8,2.4,19.9,0.0));
        foods.add(new Food("Rødkål, konserves",76.0,1.0,16.3,16.0,2.1,0.2));
        foods.add(new Food("Kalkun, kød og skind, rå",126.0,20.2,0.0,0.0,0.0,5.0));
        foods.add(new Food("Oksekød, inderlår med kappe, rå",162.0,21.1,0.0,0.0,0.0,8.6));
        foods.add(new Food("Grisekød, hakket, 10-15% fedt, råt",169.0,18.9,0.0,0.0,10.4,0.0));
        foods.add(new Food("Mayonnaise",718.0,1.1,1.4,0.0,0.0,80.0));
        foods.add(new Food("Oksekød, tykkam, rå",197.0,19.4,0.0,0.0,0.0,13.3));
        foods.add(new Food("Skinkepølse, pålæg",221.0,15.1,2.7,0.0,16.8,0.0));
        foods.add(new Food("Pumpernikkel",198.0,5.3,35.8,9.9,1.2,0.0));
        //endregion

        //Add to database
        SugarRecord.saveInTx(foods);

//        //region Measurements
//        //Calendar date, Double glucoseLevel, Integer type, Integer category, Integer beforeAfter
//        Calendar c = Calendar.getInstance();
//        List<BloodGlucoseMeasurements> bloodGlucoseMeasurements = new ArrayList<>();
//
//        c.set(2019, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE) -2);
//        c.set(Calendar.HOUR_OF_DAY, 8);
//        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 1.0, 1,1,1));
//        c.set(Calendar.HOUR_OF_DAY, 9);
//        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 4.0, 1,1,1));
//        c.set(Calendar.HOUR_OF_DAY, 10);
//        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 14.0, 1,1,1));
//        c.set(Calendar.HOUR_OF_DAY, 11);
//        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 15.0, 1,1,1));
//        c = Calendar.getInstance();
//        c.set(2019, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE) -1);
//        c.set(Calendar.HOUR_OF_DAY, 8);
//        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 1.0, 1,1,1));
//        c.set(Calendar.HOUR_OF_DAY, 9);
//        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 4.0, 1,1,1));
//        c.set(Calendar.HOUR_OF_DAY, 10);
//        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 14.0, 1,1,1));
//        c.set(Calendar.HOUR_OF_DAY, 11);
//        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 15.0, 1,1,1));
//        c = Calendar.getInstance();
//        c.set(Calendar.HOUR_OF_DAY, 4);
//        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 8.0, 1,1,1));
//        c.set(Calendar.HOUR_OF_DAY, 5);
//        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 2.0, 1,1,1));
//        c.set(Calendar.HOUR_OF_DAY, 6);
//        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 6.0, 1,1,1));
//
//        SugarRecord.saveInTx(bloodGlucoseMeasurements);
//
//        //Calendar start, Calendar end, Double value
//        Calendar c1 = Calendar.getInstance();
//        Calendar c2 = Calendar.getInstance();
//        c1.set(Calendar.MONTH, 1);
//        c1.set(Calendar.DATE, 2);
//        c1.set(Calendar.HOUR_OF_DAY, 4);
//        c2.set(Calendar.MONTH, 11);
//        c2.set(Calendar.HOUR_OF_DAY, 4);
//
//        LongTermBloodGlucose longTermBloodGlucose = new LongTermBloodGlucose(c1, c2, 6.2);
//        longTermBloodGlucose.save();
//        //endregion


        //String title, String answer, Integer type, Integer category
        List<FrequentlyAskedQuestions> FAQObjects = new ArrayList<>();
        FAQObjects.add(new FrequentlyAskedQuestions("Jeg har rejsningsproblemer",
                "For et halvt års tid siden fik jeg konstateret type 2-diabetes og blev straks sat i behandling med Metformin. På det tidspunkt havde jeg sandsynligvis haft sygdommen i flere år. " +
                        "Desværre har jeg i samme periode måttet døje med potensproblemer. Jeg kan næsten ikke få rejsning længere og har også meget svært ved at få udløsning. \n\n" +
                        "Har I eventuelt erfaring med hensyn til, hvad der kan gøres i en sådan situation? Jeg er i 50’erne og vil gerne tilbage til i hvert fald en vis seksuel aktivitet. " +
                        "Min læge er villig til at forsøge med Viagra - men måske I har kendskab til anden medicin eller andre muligheder med en vis effekt? Viagra er jo et meget kostbart præparat.\n\n" +
                        "Om det er din diabetes, der er hovedårsag til dine potensproblemer ved jeg ikke, men diabetes kan give potensproblemer. Din læge har foreslået dig Viagra. " +
                        "Ud fra mine erfaringer vil det også være mit første bud på en behandling Hvis det ikke virker, kunne du evt. bede om en henvisning til en sexolog sammen med din partner. " +
                        "Endelig findes der læger på endokrinologiske afdelinger på mange regionshospitaler, der arbejder med seksuelle problemer både for mænd og kvinder, men det kræver selvfølgelig en henvisning fra din egen læge. " +
                        "Du kan også læse mere om seksuelle komplikationer og behandlingsmuligheder på vores hjemmeside \n\n" +
                        "Jeg håber, du finder en løsning, så dit sexliv bliver tilfredsstillende. Du er også velkommen til at ringe til Diabeteslinjen og få en uddybende samtale om emnet. Tlf. 63 12 14 16.",
                0, 2 ));
        FAQObjects.add(new FrequentlyAskedQuestions("Kan man få dækket sine ekstra udgifter til kanylebokse?",
                "Nej, ikke via § 100, idet der findes en principafgørelse fra Ankestyrelsen, som siger, at udgifter til kanylebokse til bortskaffelse af brugte kanyler ikke kan dækkes efter reglerne om nødvendige merudgifter. \n\n" +
                        "Begrundelsen er, at udgifter til kanylebokse må betragtes som en omkostning, der er forbundet med brugen af kanyler. \n\n" +
                        "Der er ifølge Ankestyrelsen derfor tale om en almindelig driftsudgift i forbindelse med brug af kanyler, som ydes som hjælpemiddel.",
                0, 2));
        FAQObjects.add(new FrequentlyAskedQuestions("Kan Viagra regnes med som merudgift?",
                "Udgiften til Viagra og andre potensfremmende midler kan regnes med i dine merudgifter, hvis Sundhedsstyrelsen giver dig enkelttilskud. \n\n" +
                        "Du skal således bede din læge om at søge Sundhedsstyrelsen om enkelttilskudsbevilling.",
                1,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Får light-sodavand mit blodsukker til at stige?",
                "De fleste light-sodavand er sødet med sødestoffer, der ikke giver stigning i blodsukkeret. Det er f.eks. aspartam, acesulfam K, cyklamat og sucralose. \n\n" +
                        "I de såkaldte sportssodavand er der tilsat druesukker, som giver en stigning i blodsukkeret.\n\n" +
                        "Når du væger sodavand og andre drikke med højst 1 g kulhydrat pr. 100 g/100 ml, kan du drikke ubegrænset af dem. \n\n" +
                        "Et godt råd er dog altid at slukke den værste tørst i vand.",
                0, 1));
        FAQObjects.add(new FrequentlyAskedQuestions("Er sukkerfri chokolade godt?",
                "Chokolade - også den sukkerfri - indeholder både fedt og sukker (ca. 30 g fedt og ca. 60 g kulhydrat pr. 100 g) og giver dig dermed en stigning i blodsukkeret. " +
                        "Kulhydraterne er i form af de såkaldte sukkeralkoholer, der omsættes i kroppen og giver stigning i blodsukkeret, dog en mindre end sukker.\n\n" +
                        "Indholdet af fedt er typisk lige så højt som i almindelig chokolade. Ernæringsmæssigt er det derfor ingen fordel at vælge sukkerfri chokolade, der ofte også kaldes diætchokolade.\n\n" +
                        "Hvis du har lyst til chokolade, kan du i stedet vælge at spise et lille stykke almindelig chokolade og gerne med et højt indhold af kakao – dvs. 70 % eller derover.",
                0, 1));
        FAQObjects.add(new FrequentlyAskedQuestions("Er der kuldhydrat i nødder og mandler?",
                "Nødder og mandler indeholder også kulhydrat og kan give stigning i blodsukkeret, hvis du spiser større mængder af dem. Det kan derfor være relevant også at tælle kulhydrater i disse. " +
                        "På diabetes.dk kan du under emnet Kulhydrattælling se en liste med, hvor meget kulhydrat, der er pr. 100 g af forskellige nødder og mandler. \n\n" +
                        "Du kan også læse på varedeklarationen på de produkter, du spiser.\n\n" +
                        "Hvis du er i tvivl, vil jeg anbefale dig at snakke med diætisten på ambulatoriet.",
                1,1));
        FAQObjects.add(new FrequentlyAskedQuestions("Kan man få dækket sine medicinudgifter, hvis man er medlem af Danmark?",
                "Man kan ikke få dækket den del, der dækkes af Danmark. Så hvis man får sine medicinudgifter 100 % dækket via Danmark, så kan man ikke tage udgifterne med under sin merudgiftberegning. \n\n" +
                        "Man kan heller ikke medtage sit kontingent til Danmark.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Er hvidt hvedemel (hvid hvede) lige så sundt som f.eks. grahamsmel og rugmel?",
                "Hvid hvede er et fuldkornsmel med højt indhold af kostfibre, vitaminer og mineraler. Pga. det høje indhold af kostfibre mætter det mere end almindeligt hvedemel. " +
                        "Hvis du ønsker at bage et lyst brød eller kage og opnå næsten samme smag og farve, som ved bagning med almindeligt hvedemel, så kan du fint anvende hvid hvede som en delvis erstatning for almindelig hvedemel. \n\n" +
                        "Vi ved fra en række undersøgelser, at rug (og også havre) giver en langsom blodsukkerstigning – og specielt hvis rugbrødet indeholder hele kerner. \n\n" +
                        "Mig bekendt er der ikke lavet tilsvarende undersøgelser på hvid hvedes indflydelse på blodsukkeret. Med den viden vi har i dag, vil svaret derfor være, at rugbrød er det bedste valg til de fleste brødmåltider.",
                0,1));
        FAQObjects.add(new FrequentlyAskedQuestions("Hvilket blodsukkerapparat skal jeg vælge?",
                "Vi fra Diabeteslinjen anbefaler ikke noget bestemt apparat til blodsukkermåling. De måler alle med en nøjagtighed på ca. 10 %, men kan selvfølgelig forskellige ting. \n\n" +
                        "Jeg vil anbefale dig at vurdere, hvad du har behov for i din dagligdag og vælge apparat ud fra dine behov. Jeg har selv type 1-diabetes og er pumpebruger, og jeg har derfor behov for at kunne måle ketoner. " +
                        "Derfor har jeg et apparat, som kan måle ketoner. Men på dage, hvor blodsukkeret ter sig pænt, bruger jeg et nemmere apparat.\n\n" +
                        "Har du brug for at snakke om, hvilket apparat du kunne tænke dig, er du velkommen til at ringe til Diabeteslinjen på tlf. 63 12 14 16.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Kan man få sin merudgift med til udlandet?",
                "Nej, som udgangspunkt skal man bo i Danmark for at modtage sociale ydelser. Kun ved midlertidige ophold i udlandet på op til 4 uger kan man bevare sin merudgift.\n\n" +
                        "Som EU borger i et EU land har man dog ret til de ydelser, der findes i landet.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Må jeg fortsat spise almindelige sukkermarinerede sild?",
                "Du kan godt fortsætte med de almindelige sukkermarinerede sild, også selv om du har fået konstateret type-2 diabetes.\n\n" +
                        "sildefilet indeholder ca. 5 g sukker, og det har ingen praktisk betydning for dit blodsukker.\n\n" +
                        "Sild er en fed fisk, der giver dig mange af de gavnlige fedtstoffer. Spis gerne i alt 300 g fisk om ugen.",
                2,1));
        FAQObjects.add(new FrequentlyAskedQuestions("Hvad kan være årsagen til mine høje blodsukre?",
                "De fleste former for kulhydrat - og ikke kun sukker - får blodsukkeret til at stige. Kulhydrater får du primært fra brød, mel, gryn, kartofler, pasta, ris, frugt, mejeriprodukter, grøntsager og tørrede bælgfrugter. " +
                        "Kostfibre er også kulhydrater, men de giver ikke stigning i blodsukkeret.\n\n" +
                        "Hvis dit blodsukkeret ligger højt, kan det være en god idé at se på mængderne, du spiser, til det enkelte måltid. Jo flere kulhydrater du spiser, jo mere vil blodsukkeret nemlig stige efterfølgende.",
                2,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Hvor meget frugt må jeg spise, når jeg har diabetes?",
                "Generelt anbefales det at spise 2-3 stykker frugt om dagen, når man har diabetes. 1 stykke frugt defineres som svarende til 10 g kulhydrat. " +
                        "Det er mest hensigtsmæssigt at fordele frugten over hele dagen med 1 stk. ad gangen for at undgå for stor stigning i blodsukkeret.\n\n" +
                        "Hvis ens blodsukker er lavt, eller hvis man skal være ekstra fysisk aktiv, kan man godt spise mere end 1 stk. frugt ad gangen og mere end 2-3 stk. i alt om dagen.\n\n" +
                        "Du kan læse mere om frugt her på diabetes.dk. I spalten til højre finder du en liste med mængder af forskellige slags frugt svarende til 1 stk.",
                1,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Tæller juice med som merudgift?",
                "Det er rigtigt, at hurtigtvirkende kulhydrater som juice og druesukker kan regnes med som merudgift. Det har Ankestyrelsen slået fast i principafgørelsen 102-10 og 103-10. \n\n" +
                        "Det vil derfor være relevant at anke afgørelsen. Det er vigtigt, at du er meget konkret og præcis i din opgørelse af, hvor ofte og i hvilke situationer, du bruger hurtigtvirkende kulhydrater og dermed sandsynliggør din udgift til fx juice.",
                0,1));
        FAQObjects.add(new FrequentlyAskedQuestions("Hvilket sødemiddel kan jeg bruge til bagning?",
                "Når du bager til personer med diabetes, er det vigtigt at spare på både fedt og sukker i forhold til almindelige kager. " +
                        "De såkaldte bage-sødemidler er velegnede, da de har en sammensætning, så de erstatter sukker og samtidig giver fylde til dejen. " +
                        "På den måde kan mængden af fedtstof nedsættes, uden at det går væsentligt ud over konsistens og smag.\n\n" +
                        "Eksempler på bage-sødemidler er Perfect Fit og Atwel.",
                2,1));
        FAQObjects.add(new FrequentlyAskedQuestions("Er gigtmedicin en merudgift?",
                "Du kan regne al den medicin du får på grund af din diabetes med som merudgift. Også eksempelvis medicin for forhøjet blodtryk, forhøjet kolesterol og lign. " +
                        "Alle de præparater, som din læge har ordineret på grund af din diabetes skal indgå i merudgiftregnskabet. \n\nMedicin for anden varig sygdom/lidelse dækkes også af § 100, derfor kan din gigtmedicin også komme med.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Et tabt arbejdsfortjeneste en merudgift?",
                "Den tabte arbejdsfortjeneste accepteres ikke som en merudgift iht. § 100 i Serviceloven. Der er en principiel afgørelse fra Ankestyrelsen (C-55-03), som har fastslået dette. \n\n" +
                        "Der er derfor ikke nogen ide i, at forsøge at komme igennem med dette i din kommune.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Skal fodterpeuterne indbrette til sygusene?",
                "Ja, fodterapeuterne skal indberette den årlige fodstatus til den henvisende læge (den praktiserende læge eller læge på sygehuset).",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Mister jeg min merudgiftsstøtte, når jeg bliver folkepensionsit?",
                "Ja, du kan ikke længere modtage støtte efter § 100, når du går på folkepension.\n\n" +
                        "Diabetesforeningen arbejder sammen med de øvrige organisationer i sammenslutningen Danske Handicaporganisationer for at fjerne alderskriteriet, så både alderspensionister og førtidspensionister efter gl. ordning bliver omfattet af § 100.\n\n" +
                        "Indtil det forslag evt. vedtages, er du henvist til at søge om hjælp til dine ekstra udgifter i pensionsloven. Det vil sige helbredstillæg eller personligt tillæg, som du kan søge i kommunens Pensionsafdeling.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Er jeg dækket af det gule sygesikringsbevis på rejser uden for EU?",
                "Nej. Når du rejser uden for EU, er det nødvendigt at tegne en rejseforsikring ved et privat selskab. \n\n" +
                        "I forhold til personer med kroniske lidelser vil forsikringsselskabet som oftest opstille tilsvarende betingelser som ved rejser inden for EU dog typisk med den tilføjelse, at tidsrammen er 6 måneder frem for 2 måneder. \n\n" +
                        "Nogle forsikringsselskaber har indgået en aftale med SOS International om, at det er SOS, som står for vurderingen. Andre selskaber foretager denne vurdering selv. Uanset hvad er det en konkret vurdering, hvorvidt rejseforsikringen dækker kroniske lidelser eller ej og i hvilket omfang.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Kan man overføre bevillinger?",
                "Som udgangspunkt skal man genansøge om sociale ydelser i den kommune, man flytter til. Hvad angår hjælpemidler, skal man være opmærksom på, at der kan være forskelle i de produkter, som den nye kommune udbyder.\n\n" +
                        "Hvis man i forvejen har en bevilling på merudgifter efter servicelocens § 100, skal den kommune, man flytter til, overtage udbetalingen af bevillingen, indtil de selv har behandlet ansøgningen. " +
                        "Dette er fastslået i Ankestyrelsens principafgørelse nr. 122-09",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Kronikertilskud skal det søges?",
                "Ja. \n\n" +
                        "Såfremt du er berettiget til kroniker-tilskud, skal du gøre brug af denne mulighed.\n\n" +
                        "§ 100 i Serviceloven er subsidiær, dvs. at det kun er, hvis udgiften ikke kan dækkes efter anden lovgivning, at § 100 kan bruges.\n\n" +
                        "Mange med kronikertilskud vil forsat have merudgifter, der overstiger minimumsgrænsen, og dermed være berettiget til § 100 i Serviceloven.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Jeg modtager kontanthjælp, hvad må jeg",
                "Med hensyn til fonde og legater, så er reglerne, at såfremt beløbet er under 10.000 kr. (for enlige, 20.000 kr. for ægtefæller), så skal det ikke modregnes i din kontanthjælp. " +
                        "Men er beløbet større end 10.000 kr./20.000 kr., sker der en forholdsvis modregning.\n\n" +
                        "Dette er ifølge Aktivlovens § 33 pkt. 4 og Ligningslovens § 7 stk. 22.\n\n" +
                        "Hvis du er berettiget til handicapkompenserende ydelser iht. § 100 i Serviceloven, så skal denne ydelse ikke modregnes i din kontanthjælp. " +
                        "Hjælpen er en kompensation for merudgifter som følge af din diabetes.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Hvor meget testmarteriale er jeg berettiget til?",
                "Det er din behandlingsform, der afgør, hvor meget testmateriale du har ret til. Strimler og lancetter er hjælpemidler som falder ind under hjælpemiddelbekendtgørelsen.\n\n" +
                        "Reglen er at insulinbehandlede og de, der både får insulin og tabletter og/eller andet godkendt injektionspræparat, kan få det testmateriale, de har brug for. \n\n" +
                        "Tabletbehandlede kan få op til 150 strimler årligt, såfremt lægen anser blodsukkermåling for påkrævet. Diætbehandlede har ikke krav på testmateriale.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Kan jeg få dækket handicaprettede kurser, herunder Diabetesforeningens kurser?",
                "Ifølge vejledningen om særlig støtte til voksne står der om § 100, at der kan ydes hjælp til dækning af merudgifter til handicaprettede kurser, som tager sigte på at sætte borgeren i stand til at leve et liv som andre ikke-handicappede. \n\n" +
                        "De udgifter der kan være tale om er fx rejseudgifter, merudgifter ved måltider og ophold, kursusudgifter, kursusmaterialer eller pasning af barn/børn, som ikke skal med på kurset. Tabt arbejdsfortjeneste kan man dog ikke få dækket over § 100.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Er jeg berettiget til merudgiftsstøtte efter § 100?",
                "Du skal være insulinkrævende diabetiker mellem 18 og folkepensionistalder for at kunne komme i betragtning til merudgiftsstøtte efter § 100.\n\n" +
                        "Førtidspensionister tilkendt pension før 2003 (gl. ordning) kan ikke søge § 100, men skal søge efter Pensionsloven om personligt tillæg eller helbredstillæg.\n\n" +
                        "Flere ankesager har i øvrigt afgjort, at socialforvaltningen i din kommune ikke har pligt til at oplyse dig om f.eks. § 100 – medmindre du udtrykkeligt har gjort opmærksom på et behov for hjælp til dine merudgifter.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Hjælp til vores barn i skolen?",
                "Det er skolen (skoleforvaltningen eller kommunalbestyrelsen), der har ansvaret for, at alle børn kan blive undervist under trygge og gode forhold. Så tag en snak med skolen om, hvordan de tænker at indrette skoledagen, så jeres barn får den hjælp, han/hun har brug for.\n\n" +
                        "Hvis der skal søges om ekstra timer, er det skolen og ikke jer, der skal søge skoleforvaltningen. ",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Hvem skal betale for pumpe-infsionssæt",
                "Det er sygehuset, der skal betale for både pumpe og infusionssæt. \n\n" +
                        "Sundhedsstyrelsen har efter samråd med Indenrigs- og Sundhedsministeriet afgjort, at en insulinpumpe samt de tilhørende remedier, som er nødvendige for brugen af pumpen, er et behandlingsredskab, når patienten enten har fået undervisning i brugen af pumpen eller brugen af pumpen er under en vis kontrol af sygehuset. Behandlingsredskaber betales af sygehusvæsnet.",
                0,2));
        FAQObjects.add(new FrequentlyAskedQuestions("Diabetes og cornflakes",
                "Der er kommet flere cornflakes produkter med bl.a. højere indhold af fuldkorn og mindre sukker på markedet. \n\n" +
                        "Eksempler på disse er og Havre flakes fra Quaker Oats og AXA, sund fornuft Bran Flakes (fakta) og Cornflakes fra ISIS. Disse produkter har både Nøglehuls- og Fuldkornslogo. \n\n" +
                        "Dvs. produkterne har højt indhold af kostfibre og fuldkorn og lavt indhold af sukker, fedt og salt. Nøglehulsmærkes kan f.eks. også findes på Fitness fra Nestle, Bran flakes änglamark fra Coop og Speltflakes fra Urtekram.\n\n" +
                        "Almindelige cornflakes kan blandes med havregryn f.eks. halvt af hver. På den måde bliver måltidet også sundere og giver langsommere stigning i blodsukkeret, end hvis man kun spiser cornflakes.",
                0,1));
        SugarRecord.saveInTx(FAQObjects);

        Profile profile = new Profile();
        profile.set_idealBloodGlucoseLevel(5.5);
        profile.set_totalDailyInsulinConsumption(30.0);
        profile.set_lowerBloodGlucoseLevel(2.8);
        profile.set_upperBloodGlucoseLevel(13.0);
        profile.set_parentalControl(0);
        profile.set_bloodGlucoseMeasurement(0);
        profile.set_insulinCalc(0);
        profile.set_phoneNumber("");

        profile.save();


    }

}
