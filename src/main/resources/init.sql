CREATE TABLE vehicle_info (
                              vid VARCHAR(32) PRIMARY KEY COMMENT '车辆唯一识别码',
                              cid INT NOT NULL COMMENT '车架编号',
                              battery_type VARCHAR(20) NOT NULL COMMENT '电池类型（三元电池、铁锂电池）',
                              total_mileage BIGINT DEFAULT 0 COMMENT '总里程（单位：km）',
                              battery_health_status INT DEFAULT 100 COMMENT '电池健康状态（百分比）',
                              create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              delete_time DATETIME DEFAULT NULL COMMENT '软删除时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车辆信息表';

CREATE TABLE battery_signal (
                                vid VARCHAR(32) NOT NULL COMMENT '车辆识别码',
                                cid INT NOT NULL COMMENT '车架编号',
                                battery_type VARCHAR(20) NOT NULL COMMENT '电池类型',
                                Mx DECIMAL(10,2) DEFAULT NULL COMMENT '最大电压',
                                Mi DECIMAL(10,2) DEFAULT NULL COMMENT '最小电压',
                                Ix DECIMAL(10,2) DEFAULT NULL COMMENT '最大电流',
                                Ii DECIMAL(10,2) DEFAULT NULL COMMENT '最小电流',
                                signal_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '信号上报时间',
                                create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                KEY idx_vid_time (vid, signal_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电池信号上报表';


CREATE TABLE alarm_rule (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            rule_id INT NOT NULL COMMENT '规则编号',
                            alarm_type VARCHAR(50) NOT NULL COMMENT '报警类型（电压差、电流差）',
                            battery_type VARCHAR(20) NOT NULL COMMENT '电池类型（三元电池、铁锂电池）',
                            diff_type VARCHAR(20) NOT NULL COMMENT '差值类型（Mx-Mi、Ix-Ii）',
                            min_diff DECIMAL(10,2) NOT NULL COMMENT '最小差值（闭区间）',
                            max_diff DECIMAL(10,2) NOT NULL COMMENT '最大差值（开区间）',
                            alarm_level INT NOT NULL COMMENT '报警等级（0 级为最高）',
                            create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            delete_time DATETIME DEFAULT NULL COMMENT '软删除时间',
                            INDEX idx_battery_diff (battery_type, diff_type),
                            INDEX idx_diff_range (min_diff, max_diff)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预警规则配置表';


CREATE TABLE alarm_info (
                            vid VARCHAR(32) NOT NULL COMMENT '车辆识别码',
                            cid INT NOT NULL COMMENT '车架编号',
                            battery_type VARCHAR(20) NOT NULL COMMENT '电池类型',
                            rule_id INT NOT NULL COMMENT '命中的规则编号',
                            rule_name VARCHAR(50) NOT NULL COMMENT '规则名称',
                            alarm_level INT COMMENT '报警等级，0~4；-1 表示不报警',
                            is_alarm BOOLEAN DEFAULT TRUE COMMENT '是否报警',
                            signal_data JSON COMMENT '原始信号数据（JSON格式）',
                            signal_time DATETIME COMMENT '信号采集时间',
                            create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            INDEX idx_vid_time (vid, signal_time),
                            INDEX idx_rule_id (rule_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预警信息记录表';
