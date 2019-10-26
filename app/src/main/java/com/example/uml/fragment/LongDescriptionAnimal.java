package com.example.uml.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uml.R;
import com.example.uml.activity.core.BaseActivity;
import com.example.uml.fragment.core.BaseFragment;
import com.example.uml.info.AnimalInfoClass;
import com.example.uml.listener.OnSwipeTouchListener;
import com.example.uml.mvp.core.FragmentById;
import com.example.uml.mvp.core.FragmentData;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.uml.activity.MainActivity.currIndex;
import static com.example.uml.activity.MainActivity.listOfAnimals;

@EFragment(R.layout.long_description_fragment)
public class LongDescriptionAnimal extends BaseFragment {
    @ViewById(R.id.animalImage)
    ImageView animalImage;
    @ViewById(R.id.imageButton3)
    ImageView moneyImage;
    @ViewById(R.id.kindTextView)
    TextView kindTextView;
    @ViewById(R.id.breedTextView)
    TextView breedTextView;
    @ViewById(R.id.nameTextView)
    TextView nameTextView;
    @ViewById(R.id.sexTextView)
    TextView sexTextView;
    @ViewById(R.id.descriptionTextView)
    TextView descriptionTextView;

    int index = 0;

    void getMoreInfo(AnimalInfoClass animal) {
        animalImage.setImageBitmap(animal.getImagesOfAnimal().get(0));
        kindTextView.setText(animal.getKind());
        breedTextView.setText(animal.getBreed());
        nameTextView.setText(animal.getName());
        descriptionTextView.setText(animal.getDescription());
        sexTextView.setText(animal.isSex() ? "male" : "female");
        animalImage.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeLeft() {
                index--;
                if (index < 0) index = animal.getImagesOfAnimal().size() - 1;
                animalImage.setImageBitmap(animal.getImagesOfAnimal().get(index));
               // System.out.println(" ROFL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }

            @Override
            public void onSwipeRight() {
                index++;
                if (index > animal.getImagesOfAnimal().size() - 1) index = 0;
                animalImage.setImageBitmap(animal.getImagesOfAnimal().get(index));
                //System.out.println(" ROFL SECOND!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        });
    }


    @AfterViews
    public void mainWork() {
       /* ArrayList<Bitmap> imagesOfAnimal = new ArrayList<>();
        imagesOfAnimal.add(BitmapFactory.decodeResource(getResources(), R.mipmap.dog));
        imagesOfAnimal.add(BitmapFactory.decodeResource(getResources(), R.mipmap.money));
        AnimalInfoClass animalInfoClass = new AnimalInfoClass("Собака", "Овчарка", false, "Очко", 2, "Любит вас", imagesOfAnimal);*/
        getMoreInfo(listOfAnimals.get(currIndex));
        moneyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BaseActivity) Objects.requireNonNull(getActivity())).changeFragmentTo(new FragmentData(FragmentById.DONATE_FRAGMENT));
            }
        });
    }
}
