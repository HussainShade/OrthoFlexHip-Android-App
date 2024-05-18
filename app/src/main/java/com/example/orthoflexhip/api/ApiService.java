package com.example.orthoflexhip.api;



import com.example.orthoflexhip.apiresponse.AddMedicationResponse;
import com.example.orthoflexhip.apiresponse.ApprovedStatusResponse;
import com.example.orthoflexhip.apiresponse.ScoreCalculationResponse;
import com.example.orthoflexhip.apiresponse.ViewPatientDetailsDataResponse;
import com.example.orthoflexhip.dataClass.AddPatientData;
import com.example.orthoflexhip.dataClass.BookAppointmentData;
import com.example.orthoflexhip.dataClass.DoctorProfileData;
import com.example.orthoflexhip.dataClass.DoctorProfileDetailsData;
import com.example.orthoflexhip.dataClass.LoginData;
import com.example.orthoflexhip.dataClass.PatientDetailData;
import com.example.orthoflexhip.dataClass.PatientListData;
import com.example.orthoflexhip.dataClass.PatientMedicationData;
import com.example.orthoflexhip.dataClass.PatientMedicationListData;
import com.example.orthoflexhip.dataClass.PatientProfileData;
import com.example.orthoflexhip.dataClass.PatientViewDischargeData;
import com.example.orthoflexhip.dataClass.PatientXrayData;
import com.example.orthoflexhip.dataClass.PendingAppoinmentData;
import com.example.orthoflexhip.dataClass.RecentPatientData;
import com.example.orthoflexhip.dataClass.TrackAppointmentData;
import com.example.orthoflexhip.dataClass.UploadVideoData;
import com.example.orthoflexhip.dataClass.VideoListData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiService {
   @FormUrlEncoded
    @POST("doctorlogin.php")
    Call<LoginData> doctorLogin(
            @Field("username") String username,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("patientlogin.php")
    Call<LoginData> patientLogin(
            @Field("username") String username,
            @Field("password") String password
    );

 @FormUrlEncoded
 @POST("addpatientdetails.php")
 Call<AddPatientData> addPatientDetails(
         @Field("username") String username,
         @Field("password") String password,
         @Field("name") String name,
         @Field("age") String age,
         @Field("gender") String gender,
         @Field("height") String height,
         @Field("weight") String weight,
         @Field("problem") String problem,
         @Field("admitted_date") String admitted_date,
         @Field("discharge_date") String discharge_date,
         @Field("hospital_id") String hospital_id,
         @Field("mobile") String mobile
 );

 @FormUrlEncoded
 @POST("addfeedback.php")
 Call<AddMedicationResponse> addFeedback(
         @Field("id") String id,
         @Field("feedback") String feedback
 );

 @Multipart
 @POST("uploadvideo.php")
 Call<UploadVideoData> uploadVideo(
         @Part("video_name") RequestBody videoName,
         @Part MultipartBody.Part body
         );

 @FormUrlEncoded
 @POST("bookappoinment.php")
 Call<BookAppointmentData> bookAppointment(
         @Field("patient_id") int patientId,
         @Field("doctor_id") int doctorId,
         @Field("schedule_date") String scheduleDate,
         @Field("schedule_time") String scheduleTime,
         @Field("reason") String reason
 );

 @POST("addfeedback.php")
 Call<UploadVideoData> addFeedback(
         @Field("id") int patientId,
         @Field("feedback") String feedback
 );

 @GET("recentlyaddedpatient.php")
 Call<RecentPatientData> recentpatients();

 @GET("patientliststable.php")
 Call<PatientListData> patientList();

 @GET("pendingappointmentandroid.php")
 Call<PendingAppoinmentData> pendingAppointment();

 @GET("approvedappointmentandroid.php")
 Call<PendingAppoinmentData> approvedAppointment();

 @GET("trackappoinment.php?")
 Call<TrackAppointmentData> trackAppointment(
         @Query("patient_id") int patientId
 );

 @GET("doctorprofile.php?")
 Call<DoctorProfileData> doctorProfile(
         @Query("id") int doctorId
 );

 @GET("patientprofile.php?")
 Call<PatientProfileData> patientProfile(
         @Query("id") int patientId
 );

 @GET("viewpatientdetailsandroid.php?")
 Call<PatientDetailData> patientMedicalDetail(
         @Query("id") int patientId
 );

 @GET("patientviewmedication.php?")
 Call<PatientMedicationData> patientViewMedication(
         @Query("patient_id") int patientId
 );

 @GET("patientxrayimage.php?")
 Call<PatientXrayData> patientXrayImage(
         @Query("id") int patientId
 );

 @GET("fetchdischargesummary.php?")
 Call<PatientViewDischargeData> patientViewDischargeSummary(
         @Query("patient_id") int patientId
 );

 @GET("retrievevideo.php")
 Call<VideoListData> videoList();

 @FormUrlEncoded
 @POST("appoinmentstatuschange.php")
 Call<ApprovedStatusResponse> statusUpdate(@Field("appointment_id")String id,
                                           @Field("status")String status);
 @GET("viewpatientdetailsandroid.php")
 Call<ViewPatientDetailsDataResponse> getPatientDetails(@Query("id") String patientId);

 @Multipart
 @POST("patientimageupload.php")
 Call<ApprovedStatusResponse> patientImageUpload(@Part MultipartBody.Part body,@Part("patient_id") RequestBody id
                                                 );
 @FormUrlEncoded
 @POST("addmedication.php")
 Call<AddMedicationResponse> addPatientMedication(
         @Field("patient_id")String id,@Field("anti_edema_drugs")String antiEdemaDrugs,
         @Field("supportive_drugs")String supportiveDrugs,@Field("antibiotics")String antibiotics,
         @Field("analgesics")String analgesics,@Field("antacids")String antacids,
         @Field("tromboprophylaxis")String tromboprophylaxis
         );
 @Multipart
 @POST("prexrayupload.php")
 Call<ApprovedStatusResponse> preXray( @Part MultipartBody.Part image,@Part("patient_id") RequestBody id);

 @Multipart
 @POST("postxrayupload.php")
 Call<ApprovedStatusResponse> postXray( @Part MultipartBody.Part image,@Part("patient_id") RequestBody id);

 @Multipart
 @POST("uploaddischargesummary.php")
 Call<ApprovedStatusResponse> dischargeSummaryUpload( @Part MultipartBody.Part image,@Part("patient_id") RequestBody id);

 @FormUrlEncoded
 @POST("hipscorecalculation.php")
 Call<ScoreCalculationResponse> scoreCalculation(
         @Field("patient_id")String id, @Field("pain")int pain,
         @Field("distance_walked")int distance_walked, @Field("activities")int activities,
         @Field("public_transportation")int public_transportation, @Field("support")int support,
         @Field("limp")int limp, @Field("stairs")int stairs,
         @Field("sitting")int sitting, @Field("section_2")int section_2,
         @Field("total_degree_of_flexion")float total_degree_of_flexion,
         @Field("total_degree_of_abduction")float total_degree_of_abduction,
         @Field("total_degree_of_ext_rotation")float total_degree_of_ext_rotation,
         @Field("total_degree_of_adduction")float total_degree_of_adduction
         );

}
