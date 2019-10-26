package com.example.uml.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uml.R;
import com.example.uml.activity.core.BaseActivity;
import com.example.uml.fragment.core.BaseFragment;
import com.example.uml.info.Necessities;
import com.example.uml.mvp.core.FragmentById;
import com.example.uml.mvp.core.FragmentData;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.uml.activity.MainActivity.listOfNecessities;

@EFragment(R.layout.list_of_necessities)
public class ListOfNecessities extends BaseFragment {

    class ShortDecriptionNecessitieHolder extends RecyclerView.ViewHolder {
        ImageView NImage;
        TextView NName;
        public ShortDecriptionNecessitieHolder(View itemView){
            super(itemView);
            NImage = (ImageView) itemView.findViewById(R.id.imageNecess);
            NName = (TextView) itemView.findViewById(R.id.textNecesstiesName);
        }
        public void bind(Necessities animalInfoClass){
            NImage.setImageBitmap(animalInfoClass.getImage());
            NName.setText(animalInfoClass.getName());
            NImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((BaseActivity) Objects.requireNonNull(getActivity())).changeFragmentTo(new FragmentData(FragmentById.MAIN_FRAGMENT));
                }
            });
        }
    }

    public class ShortDescriptionNecessitieAdapter extends RecyclerView.Adapter<ShortDecriptionNecessitieHolder> {
        List<Necessities> descriptionList = new ArrayList<>();
        public void setItems(List<Necessities> descriptionListCurr) {
            descriptionList.addAll(descriptionListCurr);
            notifyDataSetChanged();
        }

        public void clearItems() {
            descriptionList.clear();
            notifyDataSetChanged();
        }
        @Override
        public ShortDecriptionNecessitieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.short_description_necessities, parent, false);
            return new ShortDecriptionNecessitieHolder(view);
        }

        @Override
        public void onBindViewHolder(ShortDecriptionNecessitieHolder holder, int position) {
            holder.bind(descriptionList.get(position));
        }

        @Override
        public int getItemCount() {
            return descriptionList.size();
        }
    }

    private ShortDescriptionNecessitieAdapter shortDescriptionNecessitieRecyclerAdapter;


    @ViewById(R.id.recyclerViewNecessities)
    RecyclerView recyclerViewN;

    @AfterViews
    public void mainWork() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.card1);
        ArrayList<Bitmap> tmp = new ArrayList<>();
        tmp.add(bmp);
      listOfNecessities.add(new Necessities("xui", bmp));
      listOfNecessities.add(new Necessities("2", bmp));
        listOfNecessities.add(new Necessities("5", bmp));
        initRecyclerView();
    }
    private void initRecyclerView() {
        recyclerViewN.setLayoutManager(new LinearLayoutManager(getActivity()));
        shortDescriptionNecessitieRecyclerAdapter = new ShortDescriptionNecessitieAdapter();
        shortDescriptionNecessitieRecyclerAdapter.setItems(listOfNecessities);
        recyclerViewN.setAdapter(shortDescriptionNecessitieRecyclerAdapter);
    }
}
