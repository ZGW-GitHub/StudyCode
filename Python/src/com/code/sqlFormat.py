# coding=utf-8

def createSql(alltsv):
    tsvs = alltsv.split('---\n')
    i = 0
    while i < len(tsvs):
        tsv = tsvs[i]
        i = i + 1
        if (isinstance(tsv, str)):
            tsvstr = str(tsv)
            tsvstr = tsvstr.replace("（", "(")
            tsvstr = tsvstr.replace("）", ")")
            cloums = tsvstr.split('\n')
            index = 2
            sql = 'create table ' + cloums[0] + ' \n(\n'
            while (index < len(cloums)):
                it = cloums[index].split('\t')
                sql += "`" + it[0] + "`  " + it[1] + "  null " + "comment '" + it[2] + "',\n"
                index += 1
            sql += ("  PRIMARY KEY (`id`)\n)" + "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='" + cloums[1] + "';")
            print sql.decode('string_escape')


table = '''exam_marking_stu_question_score
学生答题卡题目得分记录表
id	bigint(20)	主键
cardid	bigint(20)	答题卡id
groupid	bigint(20)	分组id
questionid	bigint(20)	题目id
question_type	varchar(20)	题型：单选/多选/判断/填空题/解答题/英语作文/语文作文
examid	int(11)	考试ID
courseid	int(11)	科目ID
stuid	int(11)	学生id
answer_value	varchar(1000)	答案
score	float(4,1)	得分
campusid	int(11)	校区
creatorid	int(11)	创建人id
create_time	datetime	创建时间
updatorid	int(11)	更新人id
update_time	datetime	更新时间'''

createSql(table)
