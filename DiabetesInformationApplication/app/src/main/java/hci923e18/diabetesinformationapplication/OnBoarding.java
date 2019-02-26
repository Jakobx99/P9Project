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
        sliderPage1.setBgColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage1));

        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("På alle sider findes denne knap");
        sliderPage2.setDescription("Denne knap benyttes til at gå til en side hvor du kan indrapportere dine oplevelser med de forskellige sider i appen");
        sliderPage2.setImageDrawable(R.drawable.buttonfeedback);
        sliderPage2.setBgColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("Du må gerne give så meget feedback du vil også gerne flere ting om den samme side");
        sliderPage3.setBgColor(getResources().getColor(R.color.lightRed));
        addSlide(AppIntroFragment.newInstance(sliderPage3));

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
