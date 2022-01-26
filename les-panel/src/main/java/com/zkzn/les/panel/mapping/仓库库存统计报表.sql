# smsb.client_name, 客户名称
#  货物名称
#

select smsb.client_name                          as 'clientName',
       smsb.material_desc                        as 'materialDesc',
       smsb.warehouse_name                       as 'warehouseName',
       bsp.position_code                         as 'positionCode',
       smsb.position_car_code                    as 'positionCarCode',
       sum(smsb.stock_count)                     as 'sumStockCount',
       sum(smsb.frozen_count + smsb.check_count) as 'sumFrozenCount',
       sum(smsb.pre_use_count)                   as 'sumPreUseCount',
       smsb.batch_no                             as 'batchNo',
       smsb.stock_status                         as 'stockStatus'
from s_material_storage_bin smsb
         left join b_storage_position bsp on smsb.storage_position_id = bsp.storage_position_id
where
#       smsb.client_name = '河南华溯商贸有限公司'
#   and
  smsb.material_desc = ?
  and smsb.client_name in (?)
  and smsb.warehouse_name in (?)
  and bsp.position_code = ?
  and smsb.position_car_code = ?
  and smsb.stock_status = ?
  and (smsb.stock_count + smsb.frozen_count + smsb.check_count + smsb.pre_use_count) > 0
group by smsb.client_name,
         smsb.material_desc,
         smsb.warehouse_name,
         bsp.position_code,
         smsb.position_car_code,
         smsb.stock_status,
         smsb.batch_no
order by client_name, material_desc
;

# 检索仓库
select tw.ORG_NAME as 'client_name',
      '' as 'material_desc',
      '' as 'warehouse_name',
      '' as 'position_code',
      '' as 'position_car_code',
      '' as 'stock_status'

from t_warehouse tw
where tw.STATUS = 1 and tw.ORG_NAME =