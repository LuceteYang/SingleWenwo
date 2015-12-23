package com.wenwoandroidnew.contents.magazineDetail;

        import android.graphics.drawable.Drawable;

        import com.wenwoandroidnew.system.module.ModelPicture;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by ModelLoginQuery on 2015-11-05.
 */
public class MagazineDetailImageItem {

    private List<ModelPicture> imageList = new ArrayList<>();

    public List<ModelPicture> getImageList() {
        return imageList;
    }

    public void setImageList(List<ModelPicture> imageList) {
        this.imageList = imageList;
    }

/*Drawable image;

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }*/
}
