package com.domineer.triplebro.newsreader.utils.dialogUtils;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.domineer.triplebro.newsreader.R;
import com.domineer.triplebro.newsreader.activities.AdminInfoActivity;
import com.domineer.triplebro.newsreader.fragments.MyselfFragment;
import com.domineer.triplebro.newsreader.properties.ProjectProperties;
import com.domineer.triplebro.newsreader.utils.PermissionUtil;

import java.io.File;

public class ChooseUserHeadDialogUtil {
    public static void showDialog(final MyselfFragment myselfFragment, final String phone_number, final long timeStamp) {
        boolean checkPermission = PermissionUtil.checkPermission(myselfFragment.getActivity(), "android.permission.CAMERA");
        if (!checkPermission) {
            Toast.makeText(myselfFragment.getActivity(), "未授权拍照或录像，请设置开启权限", Toast.LENGTH_SHORT).show();
            return;
        }
        checkPermission = PermissionUtil.checkPermission(myselfFragment.getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE");
        if (!checkPermission) {
            Toast.makeText(myselfFragment.getActivity(), "未授权读写手机存储，请设置开启权限", Toast.LENGTH_SHORT).show();
            return;
        }
        View view = View.inflate(myselfFragment.getActivity(), R.layout.dialog_select_photo, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(myselfFragment.getActivity());
        final AlertDialog dialog = builder.setView(view).create();
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent photo_manager = new Intent(Intent.ACTION_PICK, null);
                //打开文件
                photo_manager.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                myselfFragment.startActivityForResult(photo_manager, ProjectProperties.FROM_GALLERY);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {
            private Uri mUri;

            // 调用照相机
            @Override
            public void onClick(View v) {
                String path = myselfFragment.getActivity().getFilesDir() + File.separator + "images" + File.separator;
                File file = new File(path + phone_number + timeStamp + ".jpg");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //步骤二：Android 7.0及以上获取文件 Uri
                    mUri = FileProvider.getUriForFile(myselfFragment.getActivity(), "com.domineer.triplebro.newsreader", file);
                } else {
                    //步骤三：获取文件Uri
                    mUri = Uri.fromFile(file);
                }
                //步骤四：调取系统拍照
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                myselfFragment.startActivityForResult(intent, ProjectProperties.FROM_CAMERA);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showDialog(final AdminInfoActivity adminInfoActivity, final String phone_number, final long timeStamp) {
        boolean checkPermission = PermissionUtil.checkPermission(adminInfoActivity, "android.permission.CAMERA");
        if (!checkPermission) {
            Toast.makeText(adminInfoActivity, "未授权拍照或录像，请设置开启权限", Toast.LENGTH_SHORT).show();
            return;
        }
        checkPermission = PermissionUtil.checkPermission(adminInfoActivity, "android.permission.WRITE_EXTERNAL_STORAGE");
        if (!checkPermission) {
            Toast.makeText(adminInfoActivity, "未授权读写手机存储，请设置开启权限", Toast.LENGTH_SHORT).show();
            return;
        }
        View view = View.inflate(adminInfoActivity, R.layout.dialog_select_photo, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(adminInfoActivity);
        final AlertDialog dialog = builder.setView(view).create();
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent photo_manager = new Intent(Intent.ACTION_PICK, null);
                //打开文件
                photo_manager.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                adminInfoActivity.startActivityForResult(photo_manager, ProjectProperties.FROM_GALLERY);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {
            private Uri mUri;

            // 调用照相机
            @Override
            public void onClick(View v) {
                String path = adminInfoActivity.getFilesDir() + File.separator + "images" + File.separator;
                File file = new File(path + phone_number + timeStamp + ".jpg");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //步骤二：Android 7.0及以上获取文件 Uri
                    mUri = FileProvider.getUriForFile(adminInfoActivity, "com.domineer.triplebro.newsreader", file);
                } else {
                    //步骤三：获取文件Uri
                    mUri = Uri.fromFile(file);
                }
                //步骤四：调取系统拍照
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                adminInfoActivity.startActivityForResult(intent, ProjectProperties.FROM_CAMERA);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /*public static void showSelectSubmitDialog(final IssueFragment issueFragment, final String phone_number, final long timeStamp) {
        boolean checkPermission = PermissionUtil.checkPermission(issueFragment.getActivity(), "android.permission.CAMERA");
        if (!checkPermission) {
            Toast.makeText(issueFragment.getActivity(), "未授权拍照或录像，请设置开启权限", Toast.LENGTH_SHORT).show();
            return;
        }
        checkPermission = PermissionUtil.checkPermission(issueFragment.getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE");
        if (!checkPermission) {
            Toast.makeText(issueFragment.getActivity(), "未授权读写手机存储，请设置开启权限", Toast.LENGTH_SHORT).show();
            return;
        }
        View view = View.inflate(issueFragment.getActivity(), R.layout.dialog_select_issue, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(issueFragment.getActivity());
        final AlertDialog dialog = builder.setView(view).create();
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent photo_manager = new Intent(Intent.ACTION_PICK, null);
                //打开文件
                photo_manager.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                issueFragment.startActivityForResult(photo_manager, ProjectProperties.FROM_GALLERY);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {
            private Uri mUri;

            // 调用照相机
            @Override
            public void onClick(View v) {
                String path = issueFragment.getActivity().getFilesDir() + File.separator + "images" + File.separator;
                File file = new File(path + phone_number + timeStamp + ".jpg");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //步骤二：Android 7.0及以上获取文件 Uri
                    mUri = FileProvider.getUriForFile(issueFragment.getActivity(), "com.domineer.triplebro.microbloggraduationdesign", file);
                } else {
                    //步骤三：获取文件Uri
                    mUri = Uri.fromFile(file);
                }
                //步骤四：调取系统拍照
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                issueFragment.startActivityForResult(intent, ProjectProperties.FROM_CAMERA);
                dialog.dismiss();
            }
        });
        dialog.show();
    }*/
}
