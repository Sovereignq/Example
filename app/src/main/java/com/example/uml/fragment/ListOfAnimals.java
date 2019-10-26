package com.example.uml.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uml.R;
import com.example.uml.activity.core.BaseActivity;/*
import com.example.uml.adapter.ShortDescriptionAnimalAdapter;
import com.example.uml.adapter.ShortDesctiptionAnimalViewHolder;*/
import com.example.uml.fragment.core.BaseFragment;
import com.example.uml.info.AnimalInfoClass;
import com.example.uml.mvp.core.FragmentById;
import com.example.uml.mvp.core.FragmentData;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.uml.activity.MainActivity.currIndex;
import static com.example.uml.activity.MainActivity.listOfAnimals;


@EFragment(R.layout.list_of_animals_fragment)
public class ListOfAnimals extends BaseFragment {
    class ShortDesctiptionAnimalViewHolder extends RecyclerView.ViewHolder {


    ImageView animalImage;
    TextView animalName;
    TextView shortDescription;
    public ShortDesctiptionAnimalViewHolder(View itemView){
        super(itemView);
        animalImage = (ImageView) itemView.findViewById(R.id.imageAnimalShort);
        animalName = (TextView) itemView.findViewById(R.id.textNameAnimal);
        shortDescription = (TextView) itemView.findViewById(R.id.textShortDescriptionAnimal);
    }
    public void bind(AnimalInfoClass animalInfoClass){
        animalImage.setImageBitmap(animalInfoClass.getImagesOfAnimal().get(0));
        animalName.setText(animalInfoClass.getName());
        shortDescription.setText(animalInfoClass.getDescription());
        animalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < listOfAnimals.size(); i++)
                    if(animalInfoClass.getName().equals(listOfAnimals.get(i).getName())){
                        currIndex = i; break;
                    }
                ((BaseActivity) Objects.requireNonNull(ListOfAnimals.this.getActivity())).changeFragmentTo(new FragmentData(FragmentById.LONG_DESCRIPTION_FRAGMENT));
            }

        });
    }
}
    public class ShortDescriptionAnimalAdapter extends RecyclerView.Adapter<ShortDesctiptionAnimalViewHolder>  {

        List<AnimalInfoClass> descriptionList = new ArrayList<>();
        public void setItems(List<AnimalInfoClass> descriptionListCurr) {
            descriptionList.addAll(descriptionListCurr);
            notifyDataSetChanged();
        }

        public void clearItems() {
            descriptionList.clear();
            notifyDataSetChanged();
        }
        @Override
        public ShortDesctiptionAnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.short_description_animal, parent, false);
            return new ShortDesctiptionAnimalViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ShortDesctiptionAnimalViewHolder holder, int position) {
            holder.bind(descriptionList.get(position));
        }

        @Override
        public int getItemCount() {
            return descriptionList.size();
        }
    }



    private ShortDescriptionAnimalAdapter shortDescriptionAnimalRecyclerAdapter;

    @ViewById(R.id.recyclerViewListOfAnimals)
    RecyclerView recyclerView;

    @AfterViews
    public void mainWork() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.card1);
        ArrayList<Bitmap> tmp = new ArrayList<>();
        tmp.add(bmp);
        listOfAnimals.add(new AnimalInfoClass("1", "2", false, "First", 5,
                "so",tmp  ));
        listOfAnimals.add(new AnimalInfoClass("1", "2", false, "Second", 5,
                "mi",tmp  ));
        listOfAnimals.add(new AnimalInfoClass("1", "2", false, "Thitd", 5,
                "cen",tmp  ));
        initRecyclerView();
    }
    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        shortDescriptionAnimalRecyclerAdapter = new ShortDescriptionAnimalAdapter();
        shortDescriptionAnimalRecyclerAdapter.setItems(listOfAnimals);
        recyclerView.setAdapter(shortDescriptionAnimalRecyclerAdapter);
    }
}
