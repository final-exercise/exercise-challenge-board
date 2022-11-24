DROP DATABASE IF EXISTS ssafit;

CREATE DATABASE ssafit;

USE ssafit;

DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
	`user_seq`	INT	NOT NULL auto_increment primary key,
	`user_id`	VARCHAR(50)	NOT NULL unique,
	`user_password`	TEXT	NOT NULL,
	`user_name`	VARCHAR(30)	NOT NULL,
	`user_email`	VARCHAR(100)	NOT NULL,
	`user_birth`	DATE	NULL,
	`user_nickname`	VARCHAR(30)	NOT NULL unique,
	`user_gender`	VARCHAR(2)	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `VIDEO`;

CREATE TABLE `VIDEO` (
	`video_seq`	INT	NOT NULL auto_increment primary key,
	`video_id`	VARCHAR(30)	NOT NULL unique,
	`video_title`	VARCHAR(100)	NOT NULL,
	`video_channel_name`	VARCHAR(30)	NOT NULL,
	`video_duration`	TIME	NOT NULL,
	`video_view_cnt`	INT	NOT NULL	DEFAULT 0,
	`video_cal`	INT	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `COACH`;

CREATE TABLE `COACH` (
	`coach_seq`	INT	NOT NULL auto_increment primary key,
	`coach_id`	VARCHAR(50)	NOT NULL unique,
	`coach_password`	TEXT	NOT NULL,
	`coach_name`	VARCHAR(30)	NOT NULL,
	`coach_email`	VARCHAR(100)	NOT NULL,
	`coach_birth`	DATE	NULL,
	`coach_nickname`	VARCHAR(30)	NOT NULL unique, 
	`coach_gender`	VARCHAR(2)	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `WISH`;

CREATE TABLE `WISH` (
	`mylist_seq`	INT	NOT NULL auto_increment primary key,
	`video_seq`	INT	NOT NULL,
	`user_seq`	INT	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `USER_DIET`;

CREATE TABLE `USER_DIET` (
	`user_diet_seq`	INT	NOT NULL auto_increment primary key,
	`diet_seq`	INT	NULL,
	`diet_name`	VARCHAR(50)	NULL,
	`diet_img_url`	VARCHAR(100)	NULL,
	`diet_cal`	INT	NOT NULL	DEFAULT 0,
	`user_seq`	INT	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `CHATROOM`;

CREATE TABLE `CHATROOM` (
	`chatroom_seq`	INT	NOT NULL auto_increment primary key,
	`chatroom_title`	VARCHAR(50)	NOT NULL,
	`coach_user_seq`	INT	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `COMMENT`;
  
CREATE TABLE `COMMENT` (
	`comment_seq`	INT	NOT NULL auto_increment primary key,
	`comment_content`	VARCHAR(500)	NOT NULL,
	`video_seq`	INT	NOT NULL,
	`coach_seq`	INT	NULL,
	`user_seq`	INT	NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `COMMENT`;

CREATE TABLE `COMMENT` (
	`comment_seq`	INT	NOT NULL auto_increment primary key,
	`comment_content`	VARCHAR(500)	NOT NULL,
	`video_seq`	INT	NOT NULL,
	`coach_seq`	INT	NULL,
	`user_seq`	INT	NULL,
    `nickname` VARCHAR(30) not NULL,
    `is_coach` boolean not null,
	`comment_depth`	INT	NULL default 0,
	`bundle_id`	INT	NULL,
     `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `CHAT_MESSAGE`;

CREATE TABLE `CHAT_MESSAGE` (
	`chat_message_seq`	INT	NOT NULL auto_increment primary key,
	`message_content`	VARCHAR(255)	NOT NULL,
	`user_seq`	INT	NULL,
	`coach_seq`	INT	NULL,
	`chatroom_seq`	INT	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `USER_WORKOUT`;

CREATE TABLE `USER_WORKOUT` (
	`user_workout_seq`	INT	NOT NULL auto_increment primary key,
	`user_seq`	INT	NOT NULL,
	`video_seq`	INT	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `CHALLENGE`;

CREATE TABLE `CHALLENGE` (
	`challenge_seq`	INT	NOT NULL auto_increment primary key,
	`user_seq`	INT	NOT NULL,
	`is_public`	BOOLEAN	NOT NULL	DEFAULT FALSE,
	`duration`	DATE	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `CHALLENGE_VIDEO`;

CREATE TABLE `CHALLENGE_VIDEO` (
	`challenge_video_seq`	INT	NOT NULL auto_increment primary key,
	`challenge_seq`	INT	NOT NULL,
	`video_seq`	INT	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `USER_ACTIVITY`;

CREATE TABLE `USER_ACTIVITY` (
	`user_seq`	INT	NOT NULL,
	`user_nickname`	VARCHAR(30)	NOT NULL,
	`user_height`	DECIMAL(5,2)	NOT NULL,
	`total_attendance`	INT	NOT NULL	DEFAULT 0,
	`monthly_attendance`	INT	NOT NULL	DEFAULT 0,
	`total_reply`	INT	NOT NULL	DEFAULT 0,
	`monthly_reply`	INT	NOT NULL	DEFAULT 0,
	`total_exp`	INT	NOT NULL	DEFAULT 0,
	`monthly_exp`	INT	NOT NULL	DEFAULT 0,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `CHALLENGE_JOIN`;

CREATE TABLE `CHALLENGE_JOIN` (
	`challenge_join_seq`	INT	NOT NULL auto_increment primary key,
	`challenge_seq`	INT	NOT NULL,
	`user_seq`	INT	NOT NULL,
	`total_join`	INT	NOT NULL	DEFAULT 0,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `CHALLENGE_JOIN_DAILY`;

CREATE TABLE `CHALLENGE_JOIN_DAILY` (
	`challenge_join_daily_seq`	INT	NOT NULL auto_increment primary key,
	`challenge_join_seq`	INT	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `workout_super_type`;

CREATE TABLE `workout_super_type` (
	`workout_super_type_seq`	INT	NOT NULL,
	`workout_super_type_type`	varchar(30)	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `COACH_SUPER_TYPE`;

CREATE TABLE `COACH_SUPER_TYPE` (
	`coach_super_type_seq`	INT	NOT NULL auto_increment primary key,
	`coach_seq`	INT	NOT NULL,
	`workout_super_type_seq`	INT	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `workout_sub_type`;

CREATE TABLE `workout_sub_type` (
	`workout_sub_type_seq`	INT	NOT NULL,
	`workout_sub_type_type`	varchar(30)	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `video_super_type`;

CREATE TABLE `video_super_type` (
	`video_super_type_seq`	INT	NOT NULL auto_increment primary key,
	`video_seq`	INT	NOT NULL,
	`workout_super_type_seq`	INT	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `video_sub_type`;

CREATE TABLE `video_sub_type` (
	`video_sub_type_seq`	INT	NOT NULL auto_increment primary key,
	`video_seq`	INT	NOT NULL,
	`workout_sub_type_seq`	INT	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `coach_user`;

CREATE TABLE `coach_user` (
	`coach_user_seq`	INT	NOT NULL auto_increment primary key,
	`coach_seq`	INT	NOT NULL,
	`user_seq`	INT	NOT NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

DROP TABLE IF EXISTS `USER_BMI`;

CREATE TABLE `USER_BMI` (
	`user_bmi_seq` INT	NOT NULL auto_increment primary key,
	`user_seq`	INT	NOT NULL,
	`user_weight`	DECIMAL(5,2)	NOT NULL	DEFAULT 0.00,
	`user_bmi`	DECIMAL(5,2)	NULL,
    `created_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at`	timestamp	NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`is_valid`	boolean	NOT NULL  DEFAULT true
);

ALTER TABLE `USER_ACTIVITY` ADD CONSTRAINT `PK_USER_ACTIVITY` PRIMARY KEY (
	`user_seq`
);

ALTER TABLE `workout_super_type` ADD CONSTRAINT `PK_WORKOUT_SUPER_TYPE` PRIMARY KEY (
	`workout_super_type_seq`
);

ALTER TABLE `workout_sub_type` ADD CONSTRAINT `PK_WORKOUT_SUB_TYPE` PRIMARY KEY (
	`workout_sub_type_seq`
);

-- ALTER TABLE `USER_BMI` ADD CONSTRAINT `PK_USER_BMI` PRIMARY KEY (
-- 	`user_bmi_seq`
-- );

ALTER TABLE `WISH` ADD CONSTRAINT `FK_VIDEO_TO_WISH_1` FOREIGN KEY (
	`video_seq`
)
REFERENCES `VIDEO` (
	`video_seq`
);

ALTER TABLE `WISH` ADD CONSTRAINT `FK_USER_ACTIVITY_TO_WISH_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `USER_ACTIVITY` (
	`user_seq`
);

ALTER TABLE `USER_DIET` ADD CONSTRAINT `FK_USER_ACTIVITY_TO_USER_DIET_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `USER_ACTIVITY` (
	`user_seq`
);

ALTER TABLE `CHATROOM` ADD CONSTRAINT `FK_COACH_USER_TO_CHATROOM_1` FOREIGN KEY (
	`coach_user_seq`
)
REFERENCES `COACH_USER` (
	`coach_user_seq`
);

ALTER TABLE `COMMENT` ADD CONSTRAINT `FK_VIDEO_TO_COMMENT_1` FOREIGN KEY (
	`video_seq`
)
REFERENCES `VIDEO` (
	`video_seq`
);

ALTER TABLE `COMMENT` ADD CONSTRAINT `FK_COACH_TO_COMMENT_1` FOREIGN KEY (
	`coach_seq`
)
REFERENCES `COACH` (
	`coach_seq`
);

ALTER TABLE `COMMENT` ADD CONSTRAINT `FK_USER_ACTIVITY_TO_COMMENT_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `USER_ACTIVITY` (
	`user_seq`
);

ALTER TABLE `CHAT_MESSAGE` ADD CONSTRAINT `FK_USER_ACTIVITY_TO_CHAT_MESSAGE_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `USER_ACTIVITY` (
	`user_seq`
);

ALTER TABLE `CHAT_MESSAGE` ADD CONSTRAINT `FK_COACH_TO_CHAT_MESSAGE_1` FOREIGN KEY (
	`coach_seq`
)
REFERENCES `COACH` (
	`coach_seq`
);

ALTER TABLE `CHAT_MESSAGE` ADD CONSTRAINT `FK_CHATROOM_TO_CHAT_MESSAGE_1` FOREIGN KEY (
	`chatroom_seq`
)
REFERENCES `CHATROOM` (
	`chatroom_seq`
);

ALTER TABLE `USER_WORKOUT` ADD CONSTRAINT `FK_USER_ACTIVITY_TO_USER_WORKOUT_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `USER_ACTIVITY` (
	`user_seq`
);

ALTER TABLE `USER_WORKOUT` ADD CONSTRAINT `FK_VIDEO_TO_USER_WORKOUT_1` FOREIGN KEY (
	`video_seq`
)
REFERENCES `VIDEO` (
	`video_seq`
);

ALTER TABLE `CHALLENGE` ADD CONSTRAINT `FK_USER_ACTIVITY_TO_CHALLENGE_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `USER_ACTIVITY` (
	`user_seq`
);

ALTER TABLE `CHALLENGE_VIDEO` ADD CONSTRAINT `FK_CHALLENGE_TO_CHALLENGE_VIDEO_1` FOREIGN KEY (
	`challenge_seq`
)
REFERENCES `CHALLENGE` (
	`challenge_seq`
);

ALTER TABLE `CHALLENGE_VIDEO` ADD CONSTRAINT `FK_VIDEO_TO_CHALLENGE_VIDEO_1` FOREIGN KEY (
	`video_seq`
)
REFERENCES `VIDEO` (
	`video_seq`
);

ALTER TABLE `USER_ACTIVITY` ADD CONSTRAINT `FK_USER_TO_USER_ACTIVITY_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `USER` (
	`user_seq`
);

ALTER TABLE `CHALLENGE_JOIN` ADD CONSTRAINT `FK_CHALLENGE_TO_CHALLENGE_JOIN_1` FOREIGN KEY (
	`challenge_seq`
)
REFERENCES `CHALLENGE` (
	`challenge_seq`
);

ALTER TABLE `CHALLENGE_JOIN` ADD CONSTRAINT `FK_USER_ACTIVITY_TO_CHALLENGE_JOIN_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `USER_ACTIVITY` (
	`user_seq`
);

ALTER TABLE `CHALLENGE_JOIN_DAILY` ADD CONSTRAINT `FK_CHALLENGE_JOIN_TO_CHALLENGE_JOIN_DAILY_1` FOREIGN KEY (
	`challenge_join_seq`
)
REFERENCES `CHALLENGE_JOIN` (
	`challenge_join_seq`
);

ALTER TABLE `COACH_SUPER_TYPE` ADD CONSTRAINT `FK_COACH_TO_COACH_SUPER_TYPE_1` FOREIGN KEY (
	`coach_seq`
)
REFERENCES `COACH` (
	`coach_seq`
);

ALTER TABLE `COACH_SUPER_TYPE` ADD CONSTRAINT `FK_workout_super_type_TO_COACH_SUPER_TYPE_1` FOREIGN KEY (
	`workout_super_type_seq`
)
REFERENCES `workout_super_type` (
	`workout_super_type_seq`
);

ALTER TABLE `video_super_type` ADD CONSTRAINT `FK_VIDEO_TO_video_super_type_1` FOREIGN KEY (
	`video_seq`
)
REFERENCES `VIDEO` (
	`video_seq`
);

ALTER TABLE `video_super_type` ADD CONSTRAINT `FK_workout_super_type_TO_video_super_type_1` FOREIGN KEY (
	`workout_super_type_seq`
)
REFERENCES `workout_super_type` (
	`workout_super_type_seq`
);

ALTER TABLE `video_sub_type` ADD CONSTRAINT `FK_VIDEO_TO_video_sub_type_1` FOREIGN KEY (
	`video_seq`
)
REFERENCES `VIDEO` (
	`video_seq`
);

ALTER TABLE `video_sub_type` ADD CONSTRAINT `FK_workout_sub_type_TO_video_sub_type_1` FOREIGN KEY (
	`workout_sub_type_seq`
)
REFERENCES `workout_sub_type` (
	`workout_sub_type_seq`
);

ALTER TABLE `COACH_USER` ADD CONSTRAINT `FK_COACH_TO_COACH_USER_1` FOREIGN KEY (
	`coach_seq`
)
REFERENCES `COACH` (
	`coach_seq`
);

ALTER TABLE `COACH_USER` ADD CONSTRAINT `FK_USER_ACTIVITY_TO_COACH_USER_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `USER_ACTIVITY` (
	`user_seq`
);

ALTER TABLE `USER_BMI` ADD CONSTRAINT `FK_USER_ACTIVITY_TO_USER_BMI_1` FOREIGN KEY (
	`user_seq`
)
REFERENCES `USER_ACTIVITY` (
	`user_seq`
);

