package com.tesla.service;

        import com.github.pagehelper.PageInfo;
        import com.tesla.domain.AppointmentTime;
        import com.tesla.qo.QueryObject;

        import java.util.List;

public interface IAppointmentTimeService {
    void save(AppointmentTime appointmentTime);
    void update(AppointmentTime appointmentTime);
    void delete(Long id);
    AppointmentTime get(Long id);
    List<AppointmentTime> listAll();

    PageInfo<AppointmentTime> query(QueryObject qo);
}
