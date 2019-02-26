package hci923e18.diabetesinformationapplication;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

public class OnBoarding extends AppIntro {

    String title = "title";
    String description ="desc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        sliderPage2.setDescription("Denne knap benyttes til at gå til en side hvor du kan indrapportere dine oplevelser med de forskellige sider i appen");
        sliderPage2.setImageDrawable(R.drawable.buttonfeedback);
        sliderPage2.setBgColor(getResources().getColor(R.color.white));
        sliderPage2.setDescColor(getResources().getColor(R.color.lightRed));
        sliderPage2.setTitleColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("Rapporterings siden:");
        sliderPage3.setDescription("Siden som rapporten omhandler samt dato hentes automatisk." +
                "\n Du bedes udfylde type af rapport samt hvilke element på siden rapporten omhandler.");
        sliderPage3.setImageDrawable(R.drawable.errorpagetop);
        sliderPage3.setBgColor(getResources().getColor(R.color.white));
        sliderPage3.setDescColor(getResources().getColor(R.color.lightRed));
        sliderPage3.setTitleColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage3));

        SliderPage sliderPage4 = new SliderPage();
        sliderPage4.setTitle("Rapporterings siden:");
        sliderPage4.setDescription("Herefter bedes du beskrive hvad rapporten omhandler. \n Du bedes yderligere vælge hvor ofte du benytter denne funktionalitet samt om det var muligt at udfører den opgave du gerne ville");
        sliderPage4.setImageDrawable(R.drawable.errorpagemiddle);
        sliderPage4.setBgColor(getResources().getColor(R.color.white));
        sliderPage4.setDescColor(getResources().getColor(R.color.lightRed));
        sliderPage4.setTitleColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage4));

        SliderPage sliderPage5 = new SliderPage();
        sliderPage5.setTitle("Rapporterings siden:");
        sliderPage5.setDescription("Tilsidst vælger du hvor kraftig fejlen/problemets indflydelse var på fuldførelsen af opgaven. \n Endeligt vælger du om fejlen har en indflydelse på dit overordnet indtryk af systemet");
        sliderPage5.setImageDrawable(R.drawable.errorpagebot);
        sliderPage5.setBgColor(getResources().getColor(R.color.white));
        sliderPage5.setDescColor(getResources().getColor(R.color.lightRed));
        sliderPage5.setTitleColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage5));

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
