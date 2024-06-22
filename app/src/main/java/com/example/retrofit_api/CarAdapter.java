package com.example.retrofit_api;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder>{
  List<CarModel> carModelList;
  Context context;
  private APIService apiService;
  private CarModel carModel;

  private ActivityResultLauncher<String> activityResultLauncher;

  public CarAdapter(List<CarModel> carModelList, Context context) {
    this.carModelList = carModelList;
    this.context = context;
  }

  public void updateList(List<CarModel> newList) {
    carModelList.clear();
    carModelList.addAll(newList);
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.item_oto, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.tvName.setText(carModelList.get(position).getTen_xe_ph33056());
    holder.tvMausac.setText(String.valueOf(carModelList.get(position).getMau_sac_ph33056()));
    holder.tvGia.setText(String.valueOf(carModelList.get(position).getGia_ban_ph33056()));
    holder.tvMota.setText(carModelList.get(position).getMo_ta_ph33056());
    String imageUrl = carModelList.get(position).getHinh_anh_ph33056();
    String newUrl = imageUrl.replace("localhost", "10.0.2.2");
    Glide.with(context)
            .load(newUrl)
            .thumbnail(Glide.with(context).load(R.drawable.anh))
            .into(holder.imgAvatar);

    holder.btnSua.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        opendialogsua(position);
      }
    });

    holder.btnXoa.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        CarModel car = carModelList.get(position);
        String carId = car.get_id();
        AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
        builder.setTitle("Cảnh báo"); // Set tiêu đề cho hộp thoại
        builder.setIcon(R.drawable.baseline_wasing); // Icon cho hộp thoại
        builder.setMessage("Bạn có muốn xóa không?"); // Chuỗi thông báo
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            APIService apiService = RetrofitClientInstance.getRetrofitInstance().create(APIService.class);
            Call<Response<Void>> call = apiService.deleteCarById(carId);
            call.enqueue(new Callback<Response<Void>>() {
              @Override
              public void onResponse(Call<Response<Void>> call, Response<Response<Void>> response) {
                if (response.isSuccessful()) {
                  carModelList.remove(position);
                  notifyDataSetChanged();
                  Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                  Toast.makeText(context, "xóa thất bại", Toast.LENGTH_SHORT).show();
                }
              }

              @Override
              public void onFailure(Call<Response<Void>> call, Throwable t) {
                Log.e("Delete Error", t.getMessage());
                Toast.makeText(context, "Lỗi khi xóa dữ liệu", Toast.LENGTH_SHORT).show();
              }
            });
          }
        });
        // Nút "Không"
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
          }
        });
        // Tạo và hiển thị hộp thoại
        AlertDialog dialog = builder.create(); // Tạo hộp thoại
        dialog.show(); // Hiển thị hộp thoại
      }
    });

    holder.tvName.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Hiển thị dialog chi tiết khi bấm vào tên của xe
        showCarDetailDialog(position);
      }
    });
  }

  @Override
  public int getItemCount() {
    return carModelList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvName, tvMausac, tvGia, tvMota, btnXoa, btnSua;
    ImageView imgAvatar;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      imgAvatar = itemView.findViewById(R.id.imgAnhXe);
      tvName = itemView.findViewById(R.id.txtTenOto);
      tvMausac = itemView.findViewById(R.id.txtMausac);
      tvGia = itemView.findViewById(R.id.txtGia);
      tvMota = itemView.findViewById(R.id.txtMota);
      btnXoa = itemView.findViewById(R.id.btnXoa);
      btnSua = itemView.findViewById(R.id.btnSua);
    }
  }

  // open dialog SUA
  public void opendialogsua(int position) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.item_sua, null);
    builder.setView(view);
    AlertDialog dialog = builder.create();
    dialog.show();

    EditText edtTen = view.findViewById(R.id.edtTenOTO_sua);
    EditText edtMausac = view.findViewById(R.id.edtMausac_sua);
    EditText edtGia = view.findViewById(R.id.edtGia_sua);
    EditText edtMota = view.findViewById(R.id.edtMota_sua);
    Button btnSuas = view.findViewById(R.id.btnupdate);
    ImageView anhXe = view.findViewById(R.id.imgUPdateAnhXe);


    edtTen.setText(carModelList.get(position).getTen_xe_ph33056());
    edtMausac.setText(String.valueOf(carModelList.get(position).getMau_sac_ph33056()));
    edtGia.setText(String.valueOf(carModelList.get(position).getGia_ban_ph33056()));
    edtMota.setText(carModelList.get(position).getMo_ta_ph33056());
    String imageUrl = carModelList.get(position).getHinh_anh_ph33056();
    if (imageUrl != null && !imageUrl.isEmpty()) {
      // Chỉ thực hiện thay thế nếu chuỗi không null và không rỗng
      String newUrl = imageUrl.replace("localhost", "10.0.2.2");
      // Sử dụng Glide để tải và hiển thị hình ảnh từ newUrl
      Glide.with(context)
              .load(newUrl)
              .into(anhXe);
    } else {
      // Xử lý trường hợp không có URL hình ảnh hoặc chuỗi null bằng cách hiển thị hình ảnh mặc định
      Glide.with(context)
              .load(R.drawable.anh) // Giả sử default_image là tên của hình ảnh mặc định trong drawable
              .into(anhXe);
    }

//            String newUrl = imageUrl.replace("localhost", "10.0.2.2"); // Thay đổi nếu cần
//            Glide.with(context)
//                .load(newUrl)
//                .into(anhXe);

    anhXe.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

      }
    });
    apiService = RetrofitClientInstance.getRetrofitInstance().create(APIService.class);

    // Xử lý sự kiện khi người dùng nhấn nút "Sửa"
    btnSuas.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String tenXe = edtTen.getText().toString();
        String mausac = edtMausac.getText().toString();
        Double gia = Double.parseDouble(edtGia.getText().toString());
        String mota = edtMota.getText().toString();

        CarModel carWithoutImageRequest = new CarModel(tenXe, mausac, gia, mota, "");
        Call<Response<CarModel>> call = apiService.updateCarById(carModelList.get(position).get_id(), carWithoutImageRequest);

        call.enqueue(new Callback<Response<CarModel>>() {
          @Override
          public void onResponse(Call<Response<CarModel>> call, Response<Response<CarModel>> response) {
            if (response.isSuccessful()) {
              Toast.makeText(context, "Thành công cập nhật", Toast.LENGTH_SHORT).show();
              CarModel updatedCar = carModelList.get(position);
              updatedCar.setTen_xe_ph33056(tenXe);
              updatedCar.setMau_sac_ph33056(mausac);
              updatedCar.setGia_ban_ph33056(gia);
              updatedCar.setMo_ta_ph33056(mota);
              notifyItemChanged(position);
            } else {
              Toast.makeText(context, "Cập Nhật thất bại", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
          }

          @Override
          public void onFailure(Call<Response<CarModel>> call, Throwable t) {
            Toast.makeText(context, "Cập Nhật thất bại", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
          }
        });
      }
    });

  }

  // Tạo một hàm để hiển thị thông tin chi tiết của xe trong một dialog
  public void showCarDetailDialog(int position) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle("Thông tin chi tiết xe");
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.dialog_car_detail, null);
    builder.setView(view);

    // Ánh xạ các thành phần trong layout của dialog
    TextView tvName = view.findViewById(R.id.tv_detail_name);
    TextView tvColor = view.findViewById(R.id.tv_detail_color);
    TextView tvPrice = view.findViewById(R.id.tv_detail_price);
    TextView tvDescription = view.findViewById(R.id.tv_detail_description);
    ImageView imgAvatar = view.findViewById(R.id.img_detail_avatar);

    // Lấy thông tin của xe từ danh sách tại vị trí được chọn
    CarModel car = carModelList.get(position);

    // Đặt thông tin vào các thành phần của dialog
    tvName.setText(car.getTen_xe_ph33056());
    tvColor.setText(String.valueOf(car.getMau_sac_ph33056()));
    tvPrice.setText(String.valueOf(car.getGia_ban_ph33056()));
    tvDescription.setText(car.getMo_ta_ph33056());

    // Xử lý ảnh của xe
    String imageUrl = car.getHinh_anh_ph33056();
    String newUrl = imageUrl.replace("localhost", "10.0.2.2");
    Glide.with(context)
            .load(newUrl)
            .thumbnail(Glide.with(context).load(R.drawable.anh))
            .into(imgAvatar);

    // Hiển thị dialog
    AlertDialog dialog = builder.create();
    dialog.show();
  }

}
