package com.lokarz.funnycatcompilation.viewModel;

import androidx.lifecycle.ViewModel;

public class CatVideoModel extends ViewModel {

    private static CatVideoModel sCatVideoModel;

    public static CatVideoModel getInstance() {
        if (sCatVideoModel == null){
            sCatVideoModel = new CatVideoModel();
        }
        return sCatVideoModel;
    }


}
