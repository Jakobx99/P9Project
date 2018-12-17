package hci923e18.utility;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hci923e18.database.BloodGlucoseMeasurements;
import hci923e18.database.Food;
import hci923e18.database.FrequentlyAskedQuestions;
import hci923e18.database.LongTermBloodGlucose;
import hci923e18.database.Profile;

public class PopulateDatabase {

    /**
     * Method to populate Database with default Food items first time the app is launched
     */
    public static void populateDB(){

        //http://maddata.dk/
        // Food(String name, Double carbohydrate, Double protein, Double fiber, Double sugar)
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Cola", 10.6, 0.1, 0.0, 10.6));
        foods.add(new Food("Cola light", 0.0, 0.0,0.0,0.0));
        foods.add(new Food("Arla Letmælk", 4.7, 3.5, 0.0,4.7));
        foods.add(new Food("Ris", 28.0, 2.7,0.4, 0.1));
        foods.add(new Food("Kartofler", 17.0, 2.0, 2.2, 0.8));
        foods.add(new Food("Pasta", 75.0, 13.0, 3.2, 2.7));
        foods.add(new Food("Kyllingebryst", 0.0, 21.5, 0.0, 0.0));
        foods.add(new Food("Hakket oksekød", 0.0, 19.3, 0.0, 0.0));
        foods.add(new Food("Hakket Svinekød", 0.0, 21.2, 0.0, 0.0));
        foods.add(new Food("Mørkt rugbrød", 47.4, 5.7, 8.6, 0.0));

        //Add to database
        SugarRecord.saveInTx(foods);

        //Calendar date, Double glucoseLevel, Integer type, Integer category, Integer beforeAfter
        Calendar c = Calendar.getInstance();
        List<BloodGlucoseMeasurements> bloodGlucoseMeasurements = new ArrayList<>();
//        c.set(2018, Calendar.getInstance().get(Calendar.MONTH) - 1, Calendar.getInstance().get(Calendar.DATE));
//        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 1.0, 1,1,1));
//        c = Calendar.getInstance();

        c.set(2018, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE) -2);
        c.set(Calendar.HOUR_OF_DAY, 8);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 1.0, 1,1,1));
        c.set(Calendar.HOUR_OF_DAY, 9);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 4.0, 1,1,1));
        c.set(Calendar.HOUR_OF_DAY, 10);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 14.0, 1,1,1));
        c.set(Calendar.HOUR_OF_DAY, 11);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 15.0, 1,1,1));
        c = Calendar.getInstance();
        c.set(2018, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE) -1);
        c.set(Calendar.HOUR_OF_DAY, 8);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 1.0, 1,1,1));
        c.set(Calendar.HOUR_OF_DAY, 9);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 4.0, 1,1,1));
        c.set(Calendar.HOUR_OF_DAY, 10);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 14.0, 1,1,1));
        c.set(Calendar.HOUR_OF_DAY, 11);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 15.0, 1,1,1));
        c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 4);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 8.0, 1,1,1));
        c.set(Calendar.HOUR_OF_DAY, 5);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 2.0, 1,1,1));
        c.set(Calendar.HOUR_OF_DAY, 6);
        bloodGlucoseMeasurements.add(new BloodGlucoseMeasurements(c, 6.0, 1,1,1));

        SugarRecord.saveInTx(bloodGlucoseMeasurements);

        //Calendar start, Calendar end, Double value
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(Calendar.MONTH, 8);
        c2.set(Calendar.MONTH, 11);

        LongTermBloodGlucose longTermBloodGlucose = new LongTermBloodGlucose(c1, c2, 6.2);
        longTermBloodGlucose.save();

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
        profile.set_insulinDuration(3.5);
        profile.set_totalDailyInsulinConsumption(30.0);
        profile.set_afterBloodGlucoseLevel(9.0);
        profile.set_beforeBloodGlucoseLevel(6.0);
        profile.set_lowerBloodGlucoseLevel(2.8);
        profile.set_upperBloodGlucoseLevel(13.0);
        profile.set_parentalControl(0);
        profile.set_bloodGlucoseMeasurement(0);
        profile.set_insulinCalc(0);
        profile.set_phoneNumber("");

        profile.save();


    }

}
