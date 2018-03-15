package com.dcl.javacv.mvpproject.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dcl.javacv.mvpproject.R;
import com.dcl.javacv.mvpproject.base.BaseMvpActivity;
import com.dcl.javacv.mvpproject.contact.MainContract;
import com.dcl.javacv.mvpproject.model.http.MyRxUtils;
import com.dcl.javacv.mvpproject.model.http.MySubscriber;
import com.dcl.javacv.mvpproject.presenter.MainPresenter;
import com.dcl.javacv.mvpproject.ui.login.LoginActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.IView {

    @BindView(R.id.image)
    ImageView mImage;

        String url = "http://guolin.tech/book.png";
//    String url = "http://mmbiz.qpic.cn/mmbiz_jpg/y5HvXaQmpqnnvbkexiaA8YhhsNNqMoeibqRWicFftpVygic2ibp0qPHaWStR99EibWjc1yjkSor8PCdkylm92XcYDdoQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1";
//    String url = "http://guolin.tech/test.gif";

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        initToolbar(true, false, true).setMyTitle("主页").setMoreTitle("更多");
        basePresenter.loadData();

//        //预加载图片
//        Glide.with(this)
//                .load(url)
//                .preload();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @OnClick(R.id.go_login)
    public void onMGoLoginClicked() {
        LoginActivity.startIntentLoginActivity(this);
    }

    @OnClick(R.id.load_image)
    public void onMLoadImageClicked() {

        RequestOptions requestOptions = new RequestOptions()
//                .skipMemoryCache(true)//禁用Glide缓存功能，默认false开启
//                .placeholder(R.mipmap.default_image)
//                .circleCrop()//圆形化剪裁
                /**
                 * 利用三方库同时进行模糊化(BlurTransformation)和黑白化(GrayscaleTransformation)
                 *
                 */
                .transforms(new MaskTransformation(R.mipmap.default_image),new CropCircleTransformation())
//                .override(200,100)//指定图片尺寸，Target.SIZE_ORIGINAL加载图片原始尺寸
                /**
                 * DiskCacheStrategy.NONE   表示不缓存任何内容
                 * DiskCacheStrategy.DATA   表示只缓存原始图片
                 * DiskCacheStrategy.RESOURCE   表示只缓存转换过后的图片
                 * DiskCacheStrategy.ALL   表示既缓存原始图片，也缓存转换过后的图片
                 * DiskCacheStrategy.AUTOMATIC   表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）
                 */
//                .diskCacheStrategy(DiskCacheStrategy.NONE)//禁用掉Glide的硬盘缓存功能
                ;
        Glide.with(this)
//                .asDrawable()//asFile(),asGif(),asBitmap(),asDrawable()
                .load(url)
                .apply(requestOptions)
                .into(mImage);

//        downLoadImage();

//        Glide.with(this)
//                .load(url)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        showTipMsg(e.getMessage());
//                        return false;//返回true事件表示被处理，不会继续向下传递，不会调用后面的方法
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                })
//                .into(mImage);
    }

    /**
     * 获取加载的图片
     */
    SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
        @Override
        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
            //获取到图片进行自己的逻辑
            mImage.setImageDrawable(resource);
        }
    };

    /**
     * 下载图片，获取图片路径
     */
    private void downLoadImage() {
        Flowable.create((FlowableOnSubscribe<File>) e -> {
            Context context = getApplicationContext();
            FutureTarget<File> fileFutureTarget = Glide.with(context)
                    .asFile()
                    .load(url)
                    .submit();
            File file = fileFutureTarget.get();
            e.onNext(file);
        }, BackpressureStrategy.ERROR)
                .compose(MyRxUtils.toMain(Schedulers.io()))
                .subscribeWith(new MySubscriber<File>(this, true) {
                    @Override
                    public void onNext(File s) {
                        hideLoading();
                        showTipMsg(s.getPath());
                    }
                });
    }
}