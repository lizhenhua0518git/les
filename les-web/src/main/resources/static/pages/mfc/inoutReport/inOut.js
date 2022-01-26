function dateFormat(fmt, date) {
    let ret;
    const opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        };
    };
    return fmt;
}

/**
 * 获取当月的第一天和最后一天
 */
function theMonthOfFirstDayAndLastDay(date) {
    date = new Date(Date.parse(date.replace(/-/g, "/"))); // 开始时间
    return { firstDay: new Date(date.getFullYear(), date.getMonth(), 1), lastDay: new Date(date.getFullYear(), date.getMonth() + 1, 0) }
}

/**
*计算时间间隔
*startTime 开始时间字符串
*endTime 结束时间字符串
*/
function timeInterval(startTime, endTime) {
    if (startTime == null || endTime == null) return '-';
    startTime = new Date(Date.parse(startTime.replace(/-/g, "/"))); // 开始时间
    endTime = new Date(Date.parse(endTime.replace(/-/g, "/"))); // 结束时间

    let usedTime = endTime - startTime; // 相差的毫秒数
    let days = Math.floor(usedTime / (24 * 3600 * 1000)); // 计算出天数
    let leavel = usedTime % (24 * 3600 * 1000); // 计算天数后剩余的时间
    let hours = Math.floor(leavel / (3600 * 1000)); // 计算剩余的小时数
    let leavel2 = leavel % (3600 * 1000); // 计算剩余小时后剩余的毫秒数
    let minutes = Math.floor(leavel2 / (60 * 1000)); // 计算剩余的分钟数
    return days + '天' + hours + '时' + minutes + '分';
}


