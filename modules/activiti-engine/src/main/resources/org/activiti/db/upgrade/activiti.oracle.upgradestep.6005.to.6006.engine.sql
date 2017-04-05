alter table ACT_HI_IDENTITYLINK add column CREATE_TIME_ TIMESTAMP;
 
 update ACT_GE_PROPERTY set VALUE_ = '6.0.0.5' where NAME_ = 'schema.version';