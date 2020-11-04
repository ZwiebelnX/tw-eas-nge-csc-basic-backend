INSERT INTO `store` (`id`, `gmt_create`, `gmt_modified`, `description`, `name`)
VALUES (1, '2020-11-03 15:40:27.000000', '2020-11-03 15:40:27.000000', '想得到的东西都卖', '小陈什么都卖店'),
       (2, '2020-11-03 15:40:27.000000', '2020-11-03 15:40:27.000000', '水果水果，好吃的水果', '相思特级水果店'),
       (3, '2020-11-03 15:40:27.000000', '2020-11-03 15:40:27.000000', '智能科技，改变生活', '阿聪智能家居官方旗舰店');

INSERT INTO `user` (`id`, `gmt_create`, `gmt_modified`, `email`, `nickname`, `password`, `phone`, `real_name`, `is_admin`)
VALUES (1, NULL, NULL, 'sicong.chen@163.com', 'Chen_Onion', '12345678', '', '', false),
       (2, '2020-11-04 15:10:17.334000', '2020-11-04 15:10:17.334000', 'coupon_test@test.com', 'coupon_test', '1234', '', '', false);

INSERT INTO `coupon_type` (`id`, `gmt_create`, `gmt_modified`, `comment`, `name`)
VALUES (1, '2020-11-04 14:43:04.000000', '2020-11-04 14:43:04.000000', NULL, '商品优惠券');

INSERT INTO `coupon_info` (`id`, `gmt_create`, `gmt_modified`, `name`, `coupon_type_id`, `store_id`)
VALUES (1, '2020-11-04 14:44:31.000000', '2020-11-04 14:44:31.000000', 'B公司优惠券', 1, 2),
       (2, '2020-11-04 14:44:41.000000', '2020-11-04 14:44:41.000000', 'A公司优惠券', 1, 1),
       (3, '2020-11-04 14:44:47.000000', '2020-11-04 14:44:47.000000', 'C公司优惠券', 1, 3);

INSERT INTO `goods` (`id`, `gmt_create`, `gmt_modified`, `description`, `image_url`, `name`, `price`, `store_id`)
VALUES (1, '2020-11-03 15:48:21.000000', '2020-11-03 15:48:21.000000', '好喝的天地一号',
        'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2753706813,2645686512&fm=26&gp=0.jpg', '天地一号', 6, 1),
       (2, '2020-11-03 15:51:07.000000', '2020-11-03 15:51:07.000000', '滴露消毒液，爽呦',
        'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3015591393,3104069637&fm=26&gp=0.jpg', '滴露', 25, 1),
       (6, '2020-11-03 15:54:56.000000', '2020-11-03 15:54:56.000000', '阿迪鞋，传上就是街上最自信的仔',
        'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3924847948,2683293372&fm=26&gp=0.jpg', '阿迪达斯跑鞋', 199, 1),
       (7, '2020-11-03 15:55:34.000000', '2020-11-03 15:55:34.000000', '好吃的百奇',
        'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604400180136&di=73a5d4b6f2bac98c42d8e34d2e7f2c2b&imgtype=0&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D2341539206%2C4063176781%26fm%3D214%26gp%3D0.jpg',
        '百奇-巧克力味', 12, 1),
       (8, '2020-11-03 15:56:46.000000', '2020-11-03 15:56:46.000000', '没人能模仿我的面',
        'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604400231176&di=08967764cabcdd8117fabcb309b3d0fa&imgtype=0&src=http%3A%2F%2Fwww.szthks.com%2Flocalimg%2F687474703a2f2f6777312e616c6963646e2e636f6d2f62616f2f75706c6f616465642f69362f54316f66427946794a6358585858585858585f2121302d6974656d5f7069632e6a7067.jpg',
        '老坛酸菜面-大食袋', 25, 1),
       (9, '2020-11-03 15:57:36.000000', '2020-11-03 15:57:36.000000', '各种季节水果',
        'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604400105970&di=64f5cec5ca9e127905087245bd0411cf&imgtype=0&src=http%3A%2F%2Fimgs.bzw315.com%2FUploadFiles%2Fimage%2F20150310%2F20150310144710_9045.jpg',
        '水果杂烩', 30, 2),
       (10, '2020-11-03 15:58:07.000000', '2020-11-03 15:58:07.000000', '好吃的草莓',
        'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604400105970&di=45905cd1e06cf4e5f53cfe15c89aa1e0&imgtype=0&src=http%3A%2F%2Fwww.foodvalley.cn%2Fhome%2Fd%2F9%2Frwfpbl%2Fresource%2F2018%2F04%2F25%2F5ae0117d1c7fd.jpg',
        '大颗草莓 1*100g', 15, 2),
       (11, '2020-11-03 15:58:38.000000', '2020-11-03 15:58:38.000000', '水果店怎么能没有苹果呢',
        'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604400106892&di=5f0d9b12b04997da7976b732eeaf05fa&imgtype=0&src=http%3A%2F%2Fimage.huahuibk.com%2Fuploads%2F20190227%2F23%2F1551281830-MEXYaJFuyz.jpg',
        '大苹果', 10, 2),
       (12, '2020-11-03 15:58:58.000000', '2020-11-03 15:58:58.000000', '新鲜橙子',
        'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604400109027&di=257c02e1ecf5033f8ab771fd80656a71&imgtype=0&src=http%3A%2F%2Fimg14.360buyimg.com%2Fn1%2Fjfs%2Ft1%2F3593%2F11%2F13372%2F110693%2F5bd83714E97c3e33d%2F8791a2469ddaf441.jpg',
        '橙子', 10, 2),
       (13, '2020-11-03 15:59:35.000000', '2020-11-03 15:59:35.000000', '红心火龙果',
        'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604400427523&di=8447d556b86a06fa4b31b57eb177eff8&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F003cb3327dcd1ccf2947ddd658b9fd1d5c8b30873bc86-VMu1lS_fw658',
        '火龙果', 15, 2),
       (14, '2020-11-03 16:00:51.000000', '2020-11-03 16:00:51.000000', '很强的电冰箱',
        'https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/forum/pic/item/d8f9d72a6059252d8e08c855239b033b5bb5b940.jpg', '智能电冰箱', 5999, 3),
       (15, '2020-11-03 16:01:16.000000', '2020-11-03 16:01:16.000000', '很强的热水器',
        'https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/forum/pic/item/96dda144ad345982c59230c31bf431adcbef8443.jpg', '互联网智能热水器', 1999, 3),
       (16, '2020-11-03 16:02:04.000000', '2020-11-03 16:02:04.000000', '很热的燃气灶',
        'https://gss0.baidu.com/7LsWdDW5_xN3otqbppnN2DJv/forum/pic/item/503d269759ee3d6d68de529a54166d224f4ade43.jpg', '互联网智能燃气灶', 1999, 3),
       (17, '2020-11-03 16:02:27.000000', '2020-11-03 16:02:27.000000', '很棒的电饭煲',
        'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604400503591&di=714a762187b2ecb6757e3a14b15bb9bd&imgtype=0&src=http%3A%2F%2Fpimages3.tianjimedia.com%2Fresources%2Fproduct%2F20181203%2F1C66SB1B7X4858Z04D77375208495526.jpg',
        '互联网智能电饭煲', 599, 3);

INSERT INTO `cart` (`id`, `gmt_create`, `gmt_modified`, `amount`, `is_valid`, `goods_id`, `user_id`)
VALUES (6, '2020-11-04 14:31:52.204000', '2020-11-04 14:34:04.242000', 8, b'1', 1, 1),
       (7, '2020-11-04 14:31:53.994000', '2020-11-04 14:32:02.021000', 1, b'1', 2, 1),
       (8, '2020-11-04 14:31:55.345000', '2020-11-04 14:32:34.619000', 12, b'1', 15, 1),
       (9, '2020-11-04 14:31:56.662000', '2020-11-04 14:31:56.662000', 1, b'1', 16, 1),
       (10, '2020-11-04 14:31:57.252000', '2020-11-04 14:31:57.252000', 1, b'1', 17, 1);


INSERT INTO `user_coupon` (`id`, `gmt_create`, `gmt_modified`, `expire_time`, `user_id`, `coupon_info_id`)
VALUES (1, '2020-11-04 15:10:17.393000', '2020-11-04 15:10:17.393000', '2020-11-04 17:58:17.381000', 2, 2);
