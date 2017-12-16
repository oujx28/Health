package com.luckystar.health.modules.location;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMapOptions;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.luckystar.health.R;
import com.luckystar.health.base.BaseFragment;
import com.luckystar.health.common.utils.GDLocationUtil;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/23.
 */

public class LocationMapFragment extends BaseFragment {

    @BindView(R.id.mapView)
    MapView mMapView;
    @BindView(R.id.locationText)
    TextView mLocationTv;
    @BindView(R.id.deviceText)
    TextView mDeviceTv;
    @BindView(R.id.timeText)
    TextView mTimeTv;

    private AMap mAMap;
    private Bundle mSavedInstanceState;
    private GDLocationUtil mGDLocationUtil;

    @Override
    public void onCreateFragment(Bundle savedInstanceState) {
        setContentView(R.layout.location_map_fg);
        mSavedInstanceState = savedInstanceState;
    }

    @Override
    public void initSync() {
        mLocationTv.setVisibility(View.GONE);
        mDeviceTv.setVisibility(View.GONE);
        mTimeTv.setVisibility(View.GONE);

        mGDLocationUtil = GDLocationUtil.getInstance();

        mMapView.onCreate(mSavedInstanceState);// 此方法必须重写
        mAMap = mMapView.getMap();
        mAMap.setMapType(AMap.MAP_TYPE_NORMAL);
        mAMap.getUiSettings().setScaleControlsEnabled(true); //显示比例尺
        mAMap.getUiSettings().setZoomControlsEnabled(true); //显示缩放按钮
        mAMap.getUiSettings().setCompassEnabled(true); // 显示指南针
        mAMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
        mAMap.getUiSettings().setScrollGesturesEnabled(true); //可滑动
        mAMap.getUiSettings().setZoomGesturesEnabled(true); //可手势缩放
        mAMap.setMyLocationRotateAngle(180);
        mAMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.isInfoWindowShown()) {
                    marker.hideInfoWindow();
                }
            }
        });
        mAMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.isInfoWindowShown()) {
                    marker.hideInfoWindow();
                } else {
                    marker.showInfoWindow();
                }
                return true;
            }
        });

        // 显示当前位置
        showCurrentLocation();
    }

    /**
     * 获取失败时显示当前所在位置
     */
    private void showCurrentLocation() {
        mGDLocationUtil.startLocation(new GDLocationUtil.GDLocationReceiveListener() {
            @Override
            public void onReceiveLocation(AMapLocation location) {
                mGDLocationUtil.stopLocation();
                LatLng lat = new LatLng(location.getLatitude(), location.getLongitude());
                mAMap.moveCamera(CameraUpdateFactory.changeLatLng(lat));
                mAMap.moveCamera(CameraUpdateFactory.zoomTo(18));
            }

            @Override
            public void failureLocation() {

            }
        });
    }

    @Override
    public void onDestroy() {
        if (mMapView != null) {
            mMapView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void  onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    public static LocationMapFragment newInstance() {
        LocationMapFragment fragment = new LocationMapFragment();
        return fragment;
    }
}
