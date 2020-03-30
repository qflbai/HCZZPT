package com.yuanxin.hczzpt.platforms.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.qflbai.lib.base.activity.BaseActivity;
import com.qflbai.lib.net.RetrofitManage;
import com.qflbai.lib.net.assist.DownloadFileAssist;
import com.qflbai.lib.net.body.ServerResponseResult;
import com.qflbai.lib.net.callback.NetCallback;
import com.qflbai.lib.net.retrofit.RetrofitService;
import com.qflbai.lib.net.rxjava.NetObserver;
import com.qflbai.lib.utils.AppFileUtil;
import com.qflbai.lib.utils.DownloadAppUtil;
import com.qflbai.lib.utils.FileUtil;
import com.qflbai.lib.utils.StringUtils;
import com.qflbai.lib.utils.toast.ToastUtil;
import com.yuanxin.hczzpt.R;
import com.yuanxin.hczzpt.common.utils.CommonUtils;
import com.yuanxin.hczzpt.constant.NetApi;
import com.yuanxin.hczzpt.home.ui.activity.XyrBjActivity;
import com.yuanxin.hczzpt.platforms.adapter.NormalMultipleEntity;
import com.yuanxin.hczzpt.platforms.adapter.TeamDetailsAdapter;
import com.yuanxin.hczzpt.platforms.adapter.provider.CjrInfoProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.CsInfoProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.FuJianImgProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.LawCaseDetailsProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.LawCaseInfoProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.TitleFjProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.TitleFjTimeProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.TitleProvider;
import com.yuanxin.hczzpt.platforms.adapter.provider.YanZhiInfoProvider;
import com.yuanxin.hczzpt.platforms.bean.AjNxInfo;
import com.yuanxin.hczzpt.platforms.bean.AjXzInfo;
import com.yuanxin.hczzpt.platforms.bean.FujianTimeTitleInfo;
import com.yuanxin.hczzpt.platforms.bean.TeamDetailsInfo;
import com.yuanxin.hczzpt.platforms.bean.TeamTitleInfo;
import com.yuanxin.hczzpt.platforms.bean.XzInfo;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class TeamDetailsActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView mRv;
    private String mId;
    private List<NormalMultipleEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        initRv();
        initBackToolbar("案件详情");
        getIvBack().setImageResource(R.mipmap.ic_bs_fh);

        Intent intent = getIntent();
        mId = intent.getStringExtra("id");

    }


    private void initRv() {
        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        mRv.setLayoutManager(manager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData(mId);
    }

    private void getData(String id) {

        String path = NetApi.Path.casedetail;
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("version", "android");

        CommonUtils.removeNull(map);

        showDialogLoading();
        RetrofitManage.newInstance().createService()
                .getNet(path, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver(new NetCallback() {
                    @Override
                    public void onResponse(String s) {

                        hideDialogLoading();

                        ServerResponseResult serverResponseResult = JSON.parseObject(s, ServerResponseResult.class);
                        String code = serverResponseResult.getCode();
                        if ("200".equals(code)) {
                            Object data = serverResponseResult.getData();
                            String s1 = JSON.toJSONString(data);
                            TeamDetailsInfo teamDetailsInfo = JSON.parseObject(s1, TeamDetailsInfo.class);
                            TeamDetailsInfo.DataBean data1 = teamDetailsInfo.getData();

                            TeamDetailsInfo.DataBean.CaseBean aCase = data1.get_case();
                            List<TeamDetailsInfo.DataBean.HlistBean> hlist = data1.getHlist();
                            List<TeamDetailsInfo.DataBean.MlistBean> mlist = data1.getMlist();

                            list = new ArrayList<>();
                            TeamTitleInfo ajxq = new TeamTitleInfo();
                            ajxq.setTitle("案件详情");
                            NormalMultipleEntity titleAjxq = new NormalMultipleEntity(NormalMultipleEntity.TITLE, ajxq);
                            list.add(titleAjxq);
                            list.add(new NormalMultipleEntity(NormalMultipleEntity.LAW_CASE_DETAILS_ITEM, aCase));

                            TeamTitleInfo cjr = new TeamTitleInfo();
                            cjr.setTitle("创建人");
                            NormalMultipleEntity titleCjr = new NormalMultipleEntity(NormalMultipleEntity.TITLE, cjr);
                            list.add(titleCjr);
                            list.add(new NormalMultipleEntity(NormalMultipleEntity.CJR_INFO, aCase));

                            TeamTitleInfo ajnx = new TeamTitleInfo();
                            ajnx.setTitle("案件内信");
                            NormalMultipleEntity titleAjnx = new NormalMultipleEntity(NormalMultipleEntity.TITLE, ajnx);
                            list.add(titleAjnx);

                            String case_images = aCase.getCase_images();
                            List<String> urlList = null;
                            if (case_images != null && !TextUtils.isEmpty(case_images)) {
                                String[] split = case_images.split(",");
                                urlList = Arrays.asList(split);
                            }

                            String case_summary = aCase.getCase_summary();
                            if (urlList != null && urlList.size() > 0) {
                                for (int i = 0; i < urlList.size(); i++) {
                                    String url = urlList.get(i);
                                    AjNxInfo ajNxInfo = new AjNxInfo();
                                    if (i == 0) {
                                        ajNxInfo.setTitle(case_summary);
                                    }
                                    ajNxInfo.setUrl(NetApi.baseUrl + url);
                                    list.add(new NormalMultipleEntity(NormalMultipleEntity.LAW_CASE_INFO, ajNxInfo));
                                }
                            } else {
                                if (case_summary == null || TextUtils.isEmpty(case_summary)) {
                                    case_summary = "无";
                                }
                                AjNxInfo ajNxInfo = new AjNxInfo();
                                ajNxInfo.setTitle(case_summary);
                                list.add(new NormalMultipleEntity(NormalMultipleEntity.LAW_CASE_INFO, ajNxInfo));
                            }


                            TeamTitleInfo sdryxx = new TeamTitleInfo();
                            sdryxx.setShowAdd(true);
                            sdryxx.setTitle("涉毒人员信息");
                            NormalMultipleEntity titleSdryxx = new NormalMultipleEntity(NormalMultipleEntity.TITLE, sdryxx);
                            list.add(titleSdryxx);

                            for (TeamDetailsInfo.DataBean.MlistBean mlistBean : mlist) {
                                list.add(new NormalMultipleEntity(NormalMultipleEntity.XYR_INFO, mlistBean));
                            }

                            TeamTitleInfo yzxx = new TeamTitleInfo();
                            yzxx.setShowAdd(true);
                            yzxx.setTitle("研制信息");
                            NormalMultipleEntity titleYzxx = new NormalMultipleEntity(NormalMultipleEntity.TITLE, yzxx);
                            list.add(titleYzxx);

                            if (hlist != null) {
                                for (TeamDetailsInfo.DataBean.HlistBean hlistBean : hlist) {
                                    list.add(new NormalMultipleEntity(NormalMultipleEntity.YZ_INFO, hlistBean));


                                    list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ));

                                    FujianTimeTitleInfo fujianTimeTitleInfo = new FujianTimeTitleInfo();

                                    List<TeamDetailsInfo.DataBean.HlistBean.FilesBean> files = hlistBean.getFiles();
                                    if (files != null && files.size() > 0) {

                                        for (TeamDetailsInfo.DataBean.HlistBean.FilesBean filesBean : files) {
                                            String filesBeanPath = filesBean.getPath();
                                            String name = filesBean.getName();
                                            AjNxInfo ajNxInfo = new AjNxInfo();
                                            ajNxInfo.setTitle(name);
                                            ajNxInfo.setObject(filesBean);
                                            ajNxInfo.setUrl(NetApi.baseUrl + filesBeanPath);
                                            list.add(new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ_IMG, ajNxInfo));
                                        }
                                        fujianTimeTitleInfo.setShowAdd(true);
                                        fujianTimeTitleInfo.setObject(files);
                                    } else {
                                        fujianTimeTitleInfo.setShowAdd(false);
                                    }


                                    fujianTimeTitleInfo.setTitle("发布时间：" + hlistBean.getCreated_time());
                                    NormalMultipleEntity fjsj = new NormalMultipleEntity(NormalMultipleEntity.TITLE_FJ_TIME, fujianTimeTitleInfo);
                                    list.add(fjsj);
                                }

                            }


                            TeamDetailsAdapter teamDetailsAdapter = new TeamDetailsAdapter(list);

                            teamDetailsAdapter.setGridSpanSizeLookup(new GridSpanSizeLookup() {
                                @Override
                                public int getSpanSize(GridLayoutManager gridLayoutManager, int viewType, int position) {
                                    if (viewType == NormalMultipleEntity.TITLE_FJ_IMG) {
                                        return 1;
                                    } else {
                                        return 4;
                                    }
                                }
                            });

                            teamDetailsAdapter.addItemProvider(new CjrInfoProvider());
                            TitleProvider titleProvider = new TitleProvider();
                            titleProvider.setAddOnClick(new TitleProvider.OnItemClick() {
                                @Override
                                public void onItemClick(View view, TeamTitleInfo teamTitleInfo) {
                                    String title = teamTitleInfo.getTitle();
                                    if ("涉毒人员信息".equals(title)) {
                                        XzInfo xzInfo = new XzInfo();
                                        xzInfo.setId(aCase.getId());
                                        EventBus.getDefault().postSticky(xzInfo);
                                        Intent intent = new Intent(mContext, AddXyrActivity.class);
                                        startActivity(intent);
                                    } else if ("研制信息".equals(title)) {
                                        String id = aCase.getId();
                                        Intent intent = new Intent(mContext, AddYzxzActivity.class);
                                        intent.putExtra("id", id);
                                        startActivity(intent);
                                    }
                                }
                            });
                            teamDetailsAdapter.addItemProvider(titleProvider);
                            teamDetailsAdapter.addItemProvider(new LawCaseInfoProvider());
                            teamDetailsAdapter.addItemProvider(new LawCaseDetailsProvider());

                            CsInfoProvider csInfoProvider = new CsInfoProvider();
                            csInfoProvider.setDeleteClick(new CsInfoProvider.OnItemClick() {
                                @Override
                                public void onItemClick(View view, TeamDetailsInfo.DataBean.MlistBean mlistBean) {
                                    delete(mlistBean.getId());
                                }
                            });
                            csInfoProvider.setOnEditClick(new CsInfoProvider.OnItemClick() {
                                @Override
                                public void onItemClick(View view, TeamDetailsInfo.DataBean.MlistBean mlistBean) {
                                    Intent intent = new Intent(mContext, XyrBjActivity.class);
                                    intent.putExtra("xyr_id", mlistBean.getId());
                                    intent.putExtra("aj_id", aCase.getId());

                                    startActivity(intent);
                                }
                            });
                            teamDetailsAdapter.addItemProvider(csInfoProvider);

                            FuJianImgProvider fuJianImgProvider = new FuJianImgProvider();
                            TitleFjProvider titleFjProvider = new TitleFjProvider();
                            TitleFjTimeProvider titleFjTimeProvider = new TitleFjTimeProvider();
                            titleFjTimeProvider.setOnXz(new TitleFjTimeProvider.OnItemClick() {
                                @Override
                                public void onItemClick(View view, FujianTimeTitleInfo teamTitleInfo) {
                                    downFile((List<TeamDetailsInfo.DataBean.HlistBean.FilesBean>) teamTitleInfo.getObject());
                                }
                            });
                            YanZhiInfoProvider yanZhiInfoProvider = new YanZhiInfoProvider();
                            teamDetailsAdapter.addItemProvider(fuJianImgProvider);
                            teamDetailsAdapter.addItemProvider(titleFjProvider);
                            teamDetailsAdapter.addItemProvider(titleFjTimeProvider);
                            teamDetailsAdapter.addItemProvider(yanZhiInfoProvider);


                            mRv.setAdapter(teamDetailsAdapter);
                        }

                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                        hideDialogLoading();

                    }
                }));
    }


    @SuppressLint("CheckResult")
    public void downFile(List<TeamDetailsInfo.DataBean.HlistBean.FilesBean> files) {
        showDialogLoading();

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                for (TeamDetailsInfo.DataBean.HlistBean.FilesBean filesBeans : files) {

                    String path = filesBeans.getPath();

                    String savePath = AppFileUtil.getDownloadPath() + File.separator + filesBeans.getName();

                    RetrofitManage retrofitManage = RetrofitManage.newInstance();
                    RetrofitService service = retrofitManage.createDownloadService();
                    Observable<ResponseBody> download = service.download(path);
                    download.subscribe(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(ResponseBody responseBody) throws Exception {
                            InputStream inputStream = responseBody.byteStream();
                            File file = FileUtil.writeFile(inputStream, savePath);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            emitter.onError(throwable);
                            emitter.onComplete();
                        }
                    });
                }

                emitter.onNext("");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                        hideDialogLoading();
                        ToastUtil.showCenter(mContext, "下载成功,保存路径：" + AppFileUtil.getDownloadPath());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtil.showCenter(mContext, "下载失败");
                        hideDialogLoading();
                    }
                });
    }


    @OnClick(R.id.tv_ja)
    public void ja() {
        String path = NetApi.Path.overend;
        Map<String, Object> map = new HashMap<>();
        map.put("id", mId);
        map.put("version", "android");

        CommonUtils.removeNull(map);

        showDialogLoading();
        RetrofitManage.newInstance().createService()
                .postFormNet(path, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver(new NetCallback() {
                    @Override
                    public void onResponse(String s) {

                        hideDialogLoading();

                        ServerResponseResult serverResponseResult = JSON.parseObject(s, ServerResponseResult.class);
                        String code = serverResponseResult.getCode();
                        if ("200".equals(code)) {
                            ToastUtil.showCenter(mContext, "已结案");
                            finish();

                        }

                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                        hideDialogLoading();


                    }
                }));
    }

    private void delete(String id) {

        String path = NetApi.Path.distrustdel;
        Map<String, Object> map = new HashMap<>();
        map.put("version", "android");
        map.put("id", id);
        showDialogLoading();

        RetrofitManage.newInstance().createService()
                .postFormNet(path, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetObserver(new NetCallback() {
                    @Override
                    public void onResponse(String s) {
                        hideDialogLoading();

                        ServerResponseResult serverResponseResult = JSON.parseObject(s, ServerResponseResult.class);
                        String code = serverResponseResult.getCode();
                        if ("200".equals(code)) {
                            getData(mId);

                        }

                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                        hideDialogLoading();


                    }
                }));
    }

    @OnClick(R.id.tv_yqxtza)
    public void yqxtzn() {
        Intent intent = new Intent(mContext, XzSelectActivity.class);
        intent.putExtra("case_id", mId);
        startActivity(intent);
    }

}
