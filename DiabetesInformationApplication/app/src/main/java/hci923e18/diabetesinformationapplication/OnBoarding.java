package hci923e18.diabetesinformationapplication;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

import hci923e18.database.Identifier;

public class OnBoarding extends AppIntro {

    String title = "title";
    String description ="desc";
    Identifier i = new Identifier();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            i = Identifier.listAll(Identifier.class).get(0);
        } catch (Exception e) {

        }

        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle("DIAbetes Information Applikation");
        sliderPage1.setDescription("Tak for at du vil teste vores app");
        sliderPage1.setImageDrawable(R.drawable.logo);
        sliderPage1.setBgColor(getResources().getColor(R.color.white));
        sliderPage1.setDescColor(getResources().getColor(R.color.lightRed));
        sliderPage1.setTitleColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage1));

        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("På alle sider findes denne knap");
        sliderPage2.setDescription("Denne knap kan benyttes på alle tidspunkter til at gå til en side hvor du kan indrapportere dine oplevelser med de forskellige sider i appen, hvorefter du vil blive returneret tilbage til hvor du var i appen");
        sliderPage2.setImageDrawable(R.drawable.buttonfeedback);
        sliderPage2.setBgColor(getResources().getColor(R.color.white));
        sliderPage2.setDescColor(getResources().getColor(R.color.lightRed));
        sliderPage2.setTitleColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("Rapporterings siden:");
        sliderPage3.setDescription("Du skal ikke tænke over siden samt dato rapporten omhandler da disse udfyldes automatisk." +
                "\n Men du vælge hvilken typen fejlen/problemer er samt hvilke element på siden fejlen/problemet omhandler.");
        sliderPage3.setImageDrawable(R.drawable.errorpagetop);
        sliderPage3.setBgColor(getResources().getColor(R.color.white));
        sliderPage3.setDescColor(getResources().getColor(R.color.lightRed));
        sliderPage3.setTitleColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage3));

        SliderPage sliderPage4 = new SliderPage();
        sliderPage4.setTitle("Rapporterings siden:");
        sliderPage4.setDescription("Herefter bedes du beskrive hvad du oplevede der skete. \n Du bedes yderligere vælge hvor ofte du udfører handlingen samt om det var muligt at udfører");
        sliderPage4.setImageDrawable(R.drawable.errorpagemiddle);
        sliderPage4.setBgColor(getResources().getColor(R.color.white));
        sliderPage4.setDescColor(getResources().getColor(R.color.lightRed));
        sliderPage4.setTitleColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage4));

        SliderPage sliderPage5 = new SliderPage();
        sliderPage5.setTitle("Rapporterings siden:");
        sliderPage5.setDescription("Tilsidst vælger du hvor kraftig fejlen/problemets indflydelse var. \n Endeligt vælger du om fejlen/problemet har en indflydelse på dit overordnet indtryk af systemet. \n Når du trykker på knappen vender du tilbage til den side i appen du var på før.");
        sliderPage5.setImageDrawable(R.drawable.errorpagebot);
        sliderPage5.setBgColor(getResources().getColor(R.color.white));
        sliderPage5.setDescColor(getResources().getColor(R.color.lightRed));
        sliderPage5.setTitleColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage5));

        //If advanced make additional
        if (i.get_advanced()){

            SliderPage sliderPage7 = new SliderPage();
            sliderPage7.setTitle("Dagbog");
            sliderPage7.setDescription("Ud over at kunne rapporterer fejl på hver side beder vi dig om at lave et lille dagbogs indslag hver dag" +"\n" + "Knappen til dagbogen findes på forsiden.");
            //TODO Tag nyt billede
            sliderPage7.setImageDrawable(R.drawable.middletext);
            sliderPage7.setBgColor(getResources().getColor(R.color.white));
            sliderPage7.setDescColor(getResources().getColor(R.color.lightRed));
            sliderPage7.setTitleColor(getResources().getColor(R.color.lightRed));
            addSlide(AppIntroFragment.newInstance(sliderPage7));

            SliderPage sliderPage8 = new SliderPage();
            sliderPage8.setTitle("Dagbog");
            sliderPage8.setDescription("I dagbogen kan du skrive om dine oplevelser med appen.");
            sliderPage8.setImageDrawable(R.drawable.diaryfront);
            sliderPage8.setBgColor(getResources().getColor(R.color.white));
            sliderPage8.setDescColor(getResources().getColor(R.color.lightRed));
            sliderPage8.setTitleColor(getResources().getColor(R.color.lightRed));
            addSlide(AppIntroFragment.newInstance(sliderPage8));

            SliderPage sliderPage9 = new SliderPage();
            sliderPage9.setTitle("Dagbog");
            sliderPage9.setDescription("Vi spørger også om både de gode og dårlige ting ved appen");
            sliderPage9.setImageDrawable(R.drawable.diarygood);
            sliderPage9.setBgColor(getResources().getColor(R.color.white));
            sliderPage9.setDescColor(getResources().getColor(R.color.lightRed));
            sliderPage9.setTitleColor(getResources().getColor(R.color.lightRed));
            addSlide(AppIntroFragment.newInstance(sliderPage9));
        }

        SliderPage sliderPage6 = new SliderPage();
        sliderPage6.setTitle("Notifikationer");
        sliderPage6.setDescription("Appen sender dig 3 notifikationer i løbet af dagen for at minde dig om at benytte appen. En om morgen kl.8, en om middagen kl.13 og en om aftenen kl.20");
        sliderPage6.setImageDrawable(R.drawable.middletext);
        sliderPage6.setBgColor(getResources().getColor(R.color.white));
        sliderPage6.setDescColor(getResources().getColor(R.color.lightRed));
        sliderPage6.setTitleColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage6));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(getResources().getColor(R.color.lightRed));
        setSeparatorColor(getResources().getColor(R.color.lightRed));

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
