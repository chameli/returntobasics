declare
    stmt VARCHAR2(100);
    dropped_count integer;
begin
    dropped_count := NULL;
    while dropped_count is null or dropped_count > 0
    loop
        dropped_count := 0;
        FOR rec in (select object_name, object_type from user_objects order by object_type desc)
        LOOP
            DBMS_OUTPUT.PUT_LINE('Found ' || rec.object_name || ' of type ' || rec.object_type);
            case rec.object_type
                when 'TABLE'  THEN stmt := 'drop table ' || rec.object_name;
                when 'VIEW'  THEN stmt := 'drop view ' || rec.object_name;
                when 'SEQUENCE' THEN stmt := 'drop sequence ' || rec.object_name;
                else  stmt := null;
            end case;
            if stmt is not null then 
                begin
                    execute immediate stmt;
                    dropped_count := dropped_count + 1;
                    DBMS_OUTPUT.PUT_LINE('Dropped ' || rec.object_name || ' of type ' || rec.object_type);
                EXCEPTION  
                    WHEN OTHERS THEN
                        DBMS_OUTPUT.PUT_LINE('Failed to drop ' || rec.object_name || ' of type ' || rec.object_type || ', retrying');
                end;
            else
                DBMS_OUTPUT.PUT_LINE('Doing nothing about ' || rec.object_name || ' of type ' || rec.object_type);
            end if;
        end loop;
    end loop;    
end;
/