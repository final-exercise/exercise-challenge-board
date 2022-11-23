use ssafit;

select * from user;
insert into user (user_seq, user_id, user_password, user_name, user_email, user_birth, user_nickname, user_gender) values (0, 'ssafy', '1dFRwaOps2514T4cExvWmw==', '싸피', 'ssafy@ssafy.com', '1995-12-02', '싸피', 'M'); 
insert into user (user_seq, user_id, user_password, user_name, user_email, user_birth, user_nickname, user_gender) values (0, 'yang', '1dFRwaOps2514T4cExvWmw==', '양명균', 'yang@ssafy.com', '1995-12-02', '양띵균', 'M'); 
insert into user (user_seq, user_id, user_password, user_name, user_email, user_birth, user_nickname, user_gender) values (0, 'jii', '1dFRwaOps2514T4cExvWmw==', '김지현', 'jii@ssafy.com', '1997-03-03', '젤레나', 'F'); 
insert into user (user_seq, user_id, user_password, user_name, user_email, user_birth, user_nickname, user_gender) values (0, 'sso', '1dFRwaOps2514T4cExvWmw==', '김소정', 'sso@ssafy.com', '1997-06-06', '블레어', 'F'); 
insert into user (user_seq, user_id, user_password, user_name, user_email, user_birth, user_nickname, user_gender) values (0, 'jay', '1dFRwaOps2514T4cExvWmw==', '박재범', 'jay@ssafy.com', '1993-01-21', '제이팍', 'M'); 
insert into user (user_seq, user_id, user_password, user_name, user_email, user_birth, user_nickname, user_gender) values (0, 'simon', '1dFRwaOps2514T4cExvWmw==', '정기훈', 'simon@ssafy.com', '1984-09-09', '쌈디', 'M'); 

select * from coach;
insert into coach (coach_seq, coach_id, coach_password, coach_name, coach_email, coach_birth, coach_nickname, coach_gender) values (0, 'tubo', '1dFRwaOps2514T4cExvWmw==', '김종국', 'tubo@ssafy.com', '1977-08-25', '꾹코치', 'M');
insert into coach (coach_seq, coach_id, coach_password, coach_name, coach_email, coach_birth, coach_nickname, coach_gender) values (0, 'bbo', '1dFRwaOps2514T4cExvWmw==', '정보경', 'bbo@ssafy.com', '1996-01-19', '필라뽀스', 'F');
insert into coach (coach_seq, coach_id, coach_password, coach_name, coach_email, coach_birth, coach_nickname, coach_gender) values (0, 'doctorK', '1dFRwaOps2514T4cExvWmw==', '권한빛', 'doctorK@ssafy.com', '1974-12-25', '권선생', 'M');

select * from workout_super_type;
insert into workout_super_type (workout_super_type_seq, workout_super_type_type) values (1, '스트레칭');
insert into workout_super_type (workout_super_type_seq, workout_super_type_type) values (2, '근력강화');
insert into workout_super_type (workout_super_type_seq, workout_super_type_type) values (3, '체중감량');
insert into workout_super_type (workout_super_type_seq, workout_super_type_type) values (4, '체형교정');
insert into workout_super_type (workout_super_type_seq, workout_super_type_type) values (5, '요가');
insert into workout_super_type (workout_super_type_seq, workout_super_type_type) values (6, '골프');
insert into workout_super_type (workout_super_type_seq, workout_super_type_type) values (7, '테니스');

select * from workout_sub_type;
insert into workout_sub_type (workout_sub_type_seq, workout_sub_type_type) values (1, '전신');
insert into workout_sub_type (workout_sub_type_seq, workout_sub_type_type) values (2, '상체');
insert into workout_sub_type (workout_sub_type_seq, workout_sub_type_type) values (3, '하체');
insert into workout_sub_type (workout_sub_type_seq, workout_sub_type_type) values (4, '복부');

select * from coach_super_type;
insert into coach_super_type (coach_super_type_seq, coach_seq, workout_super_type_seq) values (0, 1, 2);
insert into coach_super_type (coach_super_type_seq, coach_seq, workout_super_type_seq) values (0, 1, 3);
insert into coach_super_type (coach_super_type_seq, coach_seq, workout_super_type_seq) values (0, 2, 3);
insert into coach_super_type (coach_super_type_seq, coach_seq, workout_super_type_seq) values (0, 2, 4);
insert into coach_super_type (coach_super_type_seq, coach_seq, workout_super_type_seq) values (0, 3, 1);
insert into coach_super_type (coach_super_type_seq, coach_seq, workout_super_type_seq) values (0, 3, 4);

select * from user_activity;
insert into user_activity (user_seq, user_nickname, user_height, total_attendance, monthly_attendance, total_reply, monthly_reply, total_exp, monthly_exp) values (1, '싸피', 180, 12, 2, 3, 3, 0, 0);
insert into user_activity (user_seq, user_nickname, user_height, total_attendance, monthly_attendance, total_reply, monthly_reply, total_exp, monthly_exp) values (2, '양띵균', 200, 2, 2, 4, 4, 0, 0);
insert into user_activity (user_seq, user_nickname, user_height, total_attendance, monthly_attendance, total_reply, monthly_reply, total_exp, monthly_exp) values (3, '젤레나', 167, 5, 1, 4, 4, 0, 0);
insert into user_activity (user_seq, user_nickname, user_height, total_attendance, monthly_attendance, total_reply, monthly_reply, total_exp, monthly_exp) values (4, '블레어', 163, 5, 5, 2, 2, 0, 0);
insert into user_activity (user_seq, user_nickname, user_height, total_attendance, monthly_attendance, total_reply, monthly_reply, total_exp, monthly_exp) values (5, '제이팍', 172, 9, 4, 2, 2, 0, 0);
insert into user_activity (user_seq, user_nickname, user_height, total_attendance, monthly_attendance, total_reply, monthly_reply, total_exp, monthly_exp) values (6, '쌈디', 176, 10, 8, 1, 1, 0, 0);

select * from user_bmi;
insert into user_bmi (user_seq, user_weight, user_bmi) values (1, 75, 23.14);
insert into user_bmi (user_seq, user_weight, user_bmi) values (2, 80, 20);
insert into user_bmi (user_seq, user_weight, user_bmi) values (3, 50, 17.92);
insert into user_bmi (user_seq, user_weight, user_bmi) values (4, 45, 16.93);
insert into user_bmi (user_seq, user_weight, user_bmi) values (5, 63, 21.29);
insert into user_bmi (user_seq, user_weight, user_bmi) values (6, 68, 21.95);

select * from `coach_user`;
insert into `coach_user` (coach_user_seq, coach_seq, user_seq) values (0, 1, 1);
insert into `coach_user` (coach_user_seq, coach_seq, user_seq) values (0, 3, 2);
insert into `coach_user` (coach_user_seq, coach_seq, user_seq) values (0, 1, 4);
insert into `coach_user` (coach_user_seq, coach_seq, user_seq) values (0, 2, 5);
insert into `coach_user` (coach_user_seq, coach_seq, user_seq) values (0, 3, 6);

select * from video;
select * from video_super_type;
select * from video_sub_type;
insert into Video (video_seq, video_id, video_title, video_channel_name, video_duration, video_view_cnt, video_cal) values (0, 'LK9e6mg4cmQ', '허벅지살 다리살 최고의 운동', 'thankyoububu', '00:10:40', 20, 100);
insert into video_super_type (video_super_type_seq, video_seq, workout_super_type_seq) values (0, 1, 3);
insert into video_sub_type (video_sub_type_seq, video_seq, workout_sub_type_seq) values (0, 1, 3);
insert into Video (video_seq, video_id, video_title, video_channel_name, video_duration, video_view_cnt, video_cal) values (0, 'yxgNF0-ORSw', '허벅지살이 찌는 이유는?', '강하나 스트레칭_stretching', '00:13:20', 23, 120);
insert into video_super_type (video_super_type_seq, video_seq, workout_super_type_seq) values (0, 2, 3);
insert into video_sub_type (video_sub_type_seq, video_seq, workout_sub_type_seq) values (0, 2, 3);
insert into Video (video_seq, video_id, video_title, video_channel_name, video_duration, video_view_cnt, video_cal) values (0, 'uYEn6p9O0XA', '묵직한 승모근격파:ok_hand:목요일엔 목스트레칭:raised_hands::raised_hands:', '강하나 스트레칭_stretching', '00:12:43', 19, 90);
insert into video_super_type (video_super_type_seq, video_seq, workout_super_type_seq) values (0, 3, 1);
insert into video_sub_type (video_sub_type_seq, video_seq, workout_sub_type_seq) values (0, 3, 2);
insert into Video (video_seq, video_id, video_title, video_channel_name, video_duration, video_view_cnt, video_cal) values (0, 'K3F7rvqSif8', '[지방탈출] -3kg 효과?! 말랑말랑 승모근 스트레칭♥', '채널A 캔버스', '00:11:28', 17, 95);
insert into video_super_type (video_super_type_seq, video_seq, workout_super_type_seq) values (0, 4, 1);
insert into video_sub_type (video_sub_type_seq, video_seq, workout_sub_type_seq) values (0, 4, 2);
insert into Video (video_seq, video_id, video_title, video_channel_name, video_duration, video_view_cnt, video_cal) values (0, 'uGJRHfbemfo', '[Balance Of Life] ※거북목 퇴치※ 몸신이 알려주는 자세 교정법 2가지! | 나는 몸신이다', '채널A 캔버스', '00:13:19', 33, 88);
insert into video_super_type (video_super_type_seq, video_seq, workout_super_type_seq) values (0, 5, 4);
insert into video_sub_type (video_sub_type_seq, video_seq, workout_sub_type_seq) values (0, 5, 2);
insert into Video (video_seq, video_id, video_title, video_channel_name, video_duration, video_view_cnt, video_cal) values (0, 'gMaB-fG4u4g', '전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]', 'thankyoububu', '00:16:54', 37, 188);
insert into video_super_type (video_super_type_seq, video_seq, workout_super_type_seq) values (0, 6, 3);
insert into video_sub_type (video_sub_type_seq, video_seq, workout_sub_type_seq) values (0, 6, 1);
insert into Video (video_seq, video_id, video_title, video_channel_name, video_duration, video_view_cnt, video_cal) values (0, 'qaBIL8NMCMo', '무.조.건! 살빠지는 댄스 다이어트', 'thankyoububu', '00:12:42', 24, 137);
insert into video_super_type (video_super_type_seq, video_seq, workout_super_type_seq) values (0, 7, 3);
insert into video_sub_type (video_sub_type_seq, video_seq, workout_sub_type_seq) values (0, 7, 1);
insert into Video (video_seq, video_id, video_title, video_channel_name, video_duration, video_view_cnt, video_cal) values (0, '7TLk7pscICk', '(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)', '소미핏 SomiFit', '00:5:12', 25, 83);
insert into video_super_type (video_super_type_seq, video_seq, workout_super_type_seq) values (0, 8, 2);
insert into video_sub_type (video_sub_type_seq, video_seq, workout_sub_type_seq) values (0, 8, 4);

select * from comment;
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '허벅지살 누가 가져가라 제발 죰~', '1', null, 1);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '왜 찌는지 저는 모름니다.', '2', null, 2);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '승모근 박살내주세요', '3', null, 2);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '내 어꺠 거의 김종국', '3', null, 3);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '딱딱한 내 승모근', '4', null, 3);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '말랑말랑~', '4', null, 4);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '거북이가 나한테 와서 친구하자고 함', '5', null, 5);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '와 이거 진짜 시원하네요', '5', null, 6);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '이게 되네?', '5', null, 1);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '겨울에 살 빼는게 맛이지', '6', null, 2);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '다이어트 가보자고', '6', null, 3);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '난 찌기 싫었어', '6', null, 4);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '무.조.건. 이 아니게 만들어 드리죠', '7', null, 1);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '댄스 댄스 춤을 춰용', '7', null, 5);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '누워서 보기만 하는 것도 되나요?', '8', null, 2);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq) values (0, '보여줄 곳 없지만 복근. 있음 좋지', '8', null, 3);

select * from wish;
insert into wish (mylist_seq, video_seq, user_seq) values (0, 1, 1);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 1, 3);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 2, 2);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 3, 1);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 3, 2);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 3, 3);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 4, 3);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 4, 4);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 4, 5);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 5, 5);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 5, 6);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 5, 1);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 6, 1);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 6, 2);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 6, 3);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 6, 4);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 6, 5);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 7, 1);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 7, 5);
insert into wish (mylist_seq, video_seq, user_seq) values (0, 8, 2);

DROP TABLE IF EXISTS COMMENT;

CREATE TABLE COMMENT (
    `comment_seq`    INT    NOT NULL auto_increment primary key,
    `comment_content`    VARCHAR(500)    NOT NULL,
    `video_seq`    INT    NOT NULL,
    `coach_seq`    INT    NULL,
    `user_seq`    INT    NULL,
    `nickname` VARCHAR(30) not NULL,
    `is_coach` boolean not null,
    `comment_depth`    INT    NULL default 0,
    `bundle_id`    INT    NULL,
    `created_at`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_valid`    boolean    NOT NULL  DEFAULT true
);


insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq, nickname, is_coach) values (0, '허벅지살 누가 가져가라 제발 죰~', '1', null, 1, '싸피', false);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq, nickname, is_coach) values (0, '왜 찌는지 저는 모름니다.', '1', null, 2, '양싸피', false);

update comment set bundle_id = comment_seq where comment_seq in (1,2);

insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq, nickname, is_coach, bundle_id, comment_depth)
values (0, '내것도', 1, null, 2, '양싸피', false, 1, 1);
insert into comment (comment_seq, comment_content, video_seq, coach_seq, user_seq, nickname, is_coach, bundle_id, comment_depth)
values (0, '내뱃살도', 1, null, 3, '아몰랑', false, 1, 1);

select * from coach;