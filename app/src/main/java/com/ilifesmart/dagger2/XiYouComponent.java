package com.ilifesmart.dagger2;

import android.app.Activity;

import javax.inject.Singleton;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Component;

@Component(modules = {XiYouModule.class})
@Singleton // 表明呗依赖的对象在应用的生命周期只有一个实例
public interface XiYouComponent {
	void inject(WuKong wk);
	void inject(Activity activity);
}
