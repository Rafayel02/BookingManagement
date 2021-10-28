drop table users, partners, partners_categories, partners_activities, reviews, activity, categories cascade;

create table if not exists users
(
    id         bigserial primary key,
    first_name text not null,
    last_name  text not null,
    email      text not null unique,
    password   text not null
);

create table if not exists partners
(
    id        bigserial primary key,
    name      text             not null,
    email     text             not null unique,
    password  text             not null,
    longitude double precision not null,
    latitude  double precision not null,
    image_url text default 'default',
    address   text             not null,
    rating    double precision
);

create table if not exists categories
(
    id   serial primary key,
    type text not null
);

create table if not exists activity
(
    id   serial primary key,
    type text not null
);

create table if not exists reviews
(
    id         bigserial not null,
    user_id    bigint    not null,
    partner_id bigint    not null,
    rating     int check (rating > 0 and rating < 6),
    comment    text,
    foreign key (user_id) references users (id),
    foreign key (partner_id) references partners (id),
    constraint reviews_user_id_partner_id_un unique (user_id, partner_id),
    constraint reviews_rating_or_comment_nn check (rating is not null or comment is not null)
);

create table if not exists partners_categories
(
    partner_id bigint not null,
    type_id    int    not null,
    foreign key (partner_id) references partners (id),
    foreign key (type_id) references categories (id)
);

create table if not exists partners_activities
(
    partner_id bigint not null,
    type_id    int    not null,
    foreign key (partner_id) references partners (id),
    foreign key (type_id) references activity (id)
);


insert into users (first_name, last_name, email, password)
values ('user1', 'lastname1', 'email1@email.com', 'Password1$');
insert into users (first_name, last_name, email, password)
values ('user2', 'lastname2', 'email2@email.com', 'Password1$');
insert into users (first_name, last_name, email, password)
values ('user3', 'lastname3', 'email3@email.com', 'Password1$');


insert into partners (name, email, password, latitude, longitude, address, rating, image_url)
values ('Los Pueblos', 'losPueblos@los.am', 'Password1$', 40.190264, 44.515949,
        '6 Tamanyan Street', 5,
        'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAB5lBMVEUtEgkAgFDGL0AfMyEIglTJMj8mCwBxIiUrEwkmEwr26MtJEQ4rAADDNkMAgU8yCgAbOCMvAAAvEQkRbURElWowAAATWjoAckAiCwATXjwuAwCwLDjPLT4Ah1AUdksXEAAGcEFVHR6ULDDk5skWZUD78tIcDgDw68kAeUrH2rekLjhyqYHLLEI0FAny6s2XKTSvy6vK0rbB2LTl6cUAAABmHSLu9NMoBgA/kmQAdT64Mz2Xw50ZAAAAVjEAaTfCMjw0Ixm+HTNpEhg2BwDoxbPu172HgHlooHYSNh0WeVUAazWyAB8AHwA5AABNAADmuKdtAARyqoEbRi5CFQ0oFQBQPjZJKyNHOCxRJyGCLzHGdHLPmIvLiH7x18a9TE+9PkfhnJjNoJDTDDbPrJm/XFzS5M2RuY/qvraPkILhzsTOfn7i2sK4ua6rxKnYdHDSUlYAEQZtjXXFGSm1UlqmCSSWjYEAQyAaIhGlV1qxdWX05dS8h3yro5N9Hx8+TjtShGI3OiyJhGohHQNcZF2NEyLXhoiPyLBJgFiq27KGrJEAcioAKwBuqW+JvZBUpHmfeG1xKzEAQhhFak2gXltla1puXlfNzsEAQCoxbU8AHQWhAACKYVi1pJGhREyZr5+KaVuFABSRfXv8a2EUAAAdv0lEQVR4nO18j1/aWLq3hER+2EQLNmAJEeVXgAiIggTEGAsFdKpT7OyOCqIt2GkZZ3fYadnr1na2t7MdZt/pdPedrdM7++Puf3qfE0ABbas76c59P2++HcQk5yT55nnO83yfkzMODKhQoUKFChUqVKhQoUKFChUqVKhQoUKFChUqVKhQoUKFChUqVKhQoUKFChUqVKhQoUKFChUqVKhQ8f8b9Hr9xbuYDCb9RTrqB0wDJrk9/DTJu0xoHzqXAb70pltTsOuWaaB9TpMHHTQMmC56b2fAALd7QZiGDQMeA+p7bug9Js+wfKG5ufauq63uc4Py4St69PtJ8ysedKEBw08naDIZ1ocuCM8t0zD6vlBHMNMt9P3RVHtH+5dffCx//XJdPmF7C7bRL+sGkwJG9EwSJImRCYw8N/we6yRGiBIj2jBJTLylpShhmE1iJJEgNqweP1wD2yxJJLoWtrXFk2WSL+4kYFvKJMhEgpBK23AIzlg+KKM2GwrYcGDcRjAECR/svCBsVk+Cse2kd9OlrfRamXljS6ZyN71bgnZfSUTIM5iAa5C3t+7cLvNwUMpI2G1e3E6jJ1AurTA8hjZQP17KbMtXCnmUYUgkErZEgj83RcRQKgXwg7LtbnbUuf3GhpUqvrdm2zYKybuSHxiifaSU3ryNrsXcqdRFaaX0SWmzsnnv72tAcWVtk5EJrhUxXimGJmCITYx7xocvxND2WXKheV9itn3L2S3pzFY8JqXzyz7w5a3sgm+7w5AnE8Yy+oXZDKYrW7VPZ1/t70cezDrTxc10TCIwEZOKaYknEUP/+IUD/Vk2xJgJCNdT5ybYYhjen6lgttvGanPtmCHfxjHD5qtM3c/fd+6HfxWyWhPtkVCpSdC0nv719ern5vCr5sKsUK1mzYHY7PUdeCBiudY6I89MWhWINP8aw0TlYLu+MRGNMolax4ZAjJD/a1PEpDtrWGJINxWqV4LE5KDnN+0bx4p3vpCk3eCDV9mHDUEQRgoFQbDMp/Yf7Hs3JemLTLl9Iem3P6MNd/6DIdZ5PmrDsFZ0wEjMv7QUHYouHfrbRL4o1UnbS4ZcJ5hi0Wadv1eUWtzr6Vq61nQEslSDHrebL2k9nNnw54IQeOEKp+8d/E5qGVtKP5r72WxoK27VmSE/uc5L23fB5zAicTj/+PMnFtzx5PMvhw9FnsB4W4kUsQlycolZubPpH7R+sSY/DJKoZ2Z/Hzh6OqbhPFp6bKxA0y66MPIUp1Lzqcj8f+5IIsmTTLlUfOb52WxIVoK7m9tDQxubOzGwDE8cal3N6s1IUwiHA9UA3QgxEBLv1L7aPByK/mYzHRRDHisv3a9t87zIfHHw6NUo/edx2kx/OXE4GdKFni39YsTFarWFsT98Or1V5zG+ft+4/XOOQ9tngWykQohM8OZziDSJoRSXj2T/sFxdqH4Tfu7juKMoCV7GBpx1jP/q69FkJTTnwQhpu3ZfkuqlwItRqtB4XFgPbaz6iSn6kNmY8E9eo+lxl5AdDW+tSNKdtEQolQ//JYbbkYUX4Kib+8t7XzEJgzneDGerC0LVXP1mIdLcb1KpYbK+m12ObN9eWcNdzkQrW/BSeu2et/mB1sya6fUNjJzKLwHDJbIB337dIp1nU9o/7pfSaRS/fk6GifpWdc+9+ztjMpyukx5zOPJ/9r7dn65mF5rTvmZ472Y1mbpBJO6Fw6Xibuzb6v1OxidWarOPXoyMXRrLhUJHi+XDxZAtFPUndIt+Maf1R+nCYiEV/s/Hd+vYz8vQxkjlzfL9nfJmRSKGxsI3F5rZqoBzqUupPBV3NpsL3yZTUUKqVLZ3NsufkS3VBn35+r3r03Eg2PCLS/RTMhR9elRIPcwtRv3+wpGNmSw8nuOajx4hE5LvhyHRQTcnvrWLIQmxxdAP0ZOAvMdjDLNBP48s7O0FuFgY1za0cV9sORB+4ajaRyYZaCOhUzGE32MC9SuC5NyLUyyt3VidFEPRKZrDzS6Xi8XzD9d1fvJwSFegNcLNfdmGTGhc6UjD8wxR9ut0upBfxBIY3yFt29C1kWjbUFZVCEziKQvsnlef7zfjVTwVr3Lh5pMqy+1nuXX5gbRO4bd6NkWRWdlpPiwUzDn/en6qvJ7CzbkhXSi0sbT+MI8fHSaOzIeHtKvw8I/hSp2pJ767pHS24LHyoWkcas9xT9TfkSY8GfUMtirTQWjdy5AgdC7q2wBFxcM3LVw2KUzH7ZZmWLBT+1l7KtTFcPzSndJWJR0TDHSD3mA2pl6uu7jGYQIjGAb8w7Z0lKcnJqYSRJRuPH74/CBdSZe+m1eYIcn4hwb1w+AZw3qD9SUj3x9vG/YM6OUC/ZbpNENyChfYsMNS3ffh+PPwckRg45HpiNkRWaC4VaLLhgZJKh78+sHNsbmxiUkdX57izKtlsaX2oJ3ob+THosA0pH1ssEQ+ul6qSCGP0l6aWB+U51/0Br3eNHgoVzDk+oAJdsi1qOEUQ8Z2JASqsezovuDELYGmsD8qvJj+ZtrejFVBlGFdDKE+rN+9fi9L0YXJI3py1eWKYl1lKcOXB9jUYdS8uEGP2fcfPCpK7yHSRD23gNn4gPUqojjuR3JMN24ymTzWYRnWUzYMpbLTy8tVvBoPUM2DbzghmLU3n1fjvuWFSDxlI7oZMkzF98JuZoeIxacv6fwQyYvHBDFRJBINXKvLTfAfsJw9EFxhFGZIYozfCgXjoM5GkqFrhgH9wBBJEOSwSW+ybpCiPL1QJvoZHi6E43lhLzY9PR1r3txfrmbDsen96ViW4wLJ1LMuhgMJQtp5kst5aD8jkgZcmwALnlSlaKohVOCWQJPqaI/2T/sVAmKpkgwxkYnO6QcGdXK1778Gx6wJkbB59CZDVOzOHj0Ml9hAcrnpc8zm/hivWpLOQNyH/zo3y/myy+FR8+EJQ8+gjVmpCVBDLG48PfTTx8dOwBBDGm1iYmqjMPI4l92qi8qOQ+AwPAD2ankOgxzEs4FiIJoHYAjxbIbiEmsJ+qb/VCjkG4U/V5cD31QLBS2uLTz0OasLji6Gg9bNSu3TfW4+FR3KL0a5HHaKoQg+71o6cum+1Grtvk/T2/9XURuCH4K5PDpRnlkiEuCxBh2D+ceHB8YnGZHAjj2qx4ZRRzgb/r5AzzUMrtfTcSr7KOeaa8zTuU+a3+6nuhha57dKn8xWLfkxnX8o1MgPYWjOTZ52w9oTfRhRzrHRw3ViykGNfjr7Kv3dvKIMmTYpscVwUN4gbciGE0jRiGJbA/TEUl3K903kS5p+4P6I1f79JveH70fMf3FfHyv8ObkcoSePRQN4qWi79+rvlMYVEhn/kXnxTDTwKSivohxuqb66W1c4WzDkHJCaaDMMDaK5VEj36x6TaXw95PdDQ4w8ZcON1Lf7M2xu/vva4vzDJMTTQGH+o9pf5h6OTkduHvm7GSbqm8llQQAdkLAdsZyFY1kWPhzAwXGCIKDfuEbCllhdoIRl5wozqayXEtiEfsBklW+KICYgAY77RUacHEQvEzweq2cYghDZb8NEbqEaCfzxB3o2/zhVpY6y4T8VzLMu18NkpOpYTPQw5LdrtXs1KI3Sd53eTC0DqGVq3loNPplMa9PoTKdL6cy9Ui0tMQrbkMcmPQaTYcqP4nbUqh82QI7kRX5icGAKZIDJNDBoQtNlRI8NmaHPk8tVbWFkkH79eXhh1hH5PlcYLBw9rn4z/cMS0R1LQdNC+SutSNuVyp3fSCvbW+n0mi2xtiKtpP+rAryLX9TrW5uVSnmlLgGUrp5IHupR0DMmz/rL6LAVUv74JE+Cz5IT47LQMUGgHYehSPQq79Dj6vNp5/cpYeRRxPH6rx+w1U8eWv70+9j+aDXn78yHdxhijFhOZ9I7a8ZfbZdK3w1d+fEAzXNLmUrp4xsf75Z2pd30zm46sysxqKJRmiEv2qBaMZhAhCI+hgkShiEKMZPr89ZBqx6ODQ+V+d7aQuQHn4R9jojTVw0IWrqaGhGaEV8svDATfj6EET0MwWWl+6XKs6XV1b8WMz/eeLaZ/vi7O2sSJqZrSz+m/7a0+l1p98fV1aVnxdr2e6nx0fuTAQNo0FsG04BnqNxxMRh6/snJqBWIj9vEXi8lRH/OEtnzPXFYWHzk4d9zBYp1CIGvm2HLa9txpmsxJDDmfuaZieZGzPvpf6ySUmn3rxOlNWTD9FypmL7tn72Tjo8VzEer26Uy9h7maQiG96/LRefV8cEoeWwneTYbSl243vjhKYaEjmabcU2h0WC51Ccp1jzeKFDZJncUwvoYikw582zejOdHzMli5uWVw2Im+o80YpjOrP6jVFm68WNmN04XcJy+8dualHgPyhvEGTk5oR/UQ71NkF2iEaVmkURvayeYvvoQCvfoolXjyuEOLWWuWvDXrF3LsoOLOr6fISGlN2+Zx1lKSyfvb2fu/nhl/sfSb2WGm+mhy1Pflda+SMdTBdyldX24U1RaeUO2k6cwWkmPZ0QGUi8BSgP5Ko+iKoneRPcxBBeGPuSQhqa1tBYXvhbwBp0ruNglrEvndWoLW+m/xzRsY31j4tevihA+M5n09vaaxEuZerFUSu+WV9LNeZ1ucURD5f6WVrp6QqKpD6IoMgwIttYGY0OaJ0r0MmxhKD9WoAtj+VQ1lacL9IiZXep5l9VmuL22xFLUOkHwz1K+z/77xuyVOR1Tk6QKiqeSdHslnfwAkpVNa9fQhyWlGfI9Pil/gelEEWsJGRKzDbVEAHEWQ3zso78s/nP++uyD2X/OXv/l2Qylz7ZWc9qREJMQ/YMfvLp/I4/nEuL9zFptW/aSlbVPXq8jN1ofGdEulURGYRsyieE+mHQ8Exowtbc8hmFoB+njTIapR+4Dd81dizndtYMHHYZ821cZEY1DZnNnlWXNEyTD2GjWMf0fR65D8baU2JYYGBu8tNV0sNoyk2AWOY4+VN5LQXnrezGoI5jJ453odap1kuzNh21LDbGpR5/88tH8p7Pwb/yvxwzJdkZkWm+5ydKHY5R9/NDvv/QDpbm+s2RA4xhGAkMm0PvGI9ZOr/ptSyN29mEoU38PDPuOyQwHT6a79NYNdLtnMcynHk0/qP2+9j18rtfaDHnI2mJlWyLlVxDA8HbGn9NoafM4bW6wqX9u2SbJ0GQLkDulu7/gxhqOkQY7QlM3vtq9rXgsJcf7VsF4EEPP8ab1WohgOroUbr0iSdubZYZve2nw0+Cjg0du+Be8DrGUQK9Ifb6DO+nadG1bzhYMtrn2YY6m8Bye555eKxYlXjfxUvcS4eNJRspsP9VqNC4ap0YaoUxCUU1jME2BAp2I9mJiEkTo8ZYu1LYbem9BkLU7oJXTO6UK2rXOsY3FxpeNxuvF3OLrRS3OondPO8mFcKW+UuLYmiTP6jO2dPHDAmf/kuXo1b8Zt3gy+rKDSciWmWeLDvsYTZlzz9JFXmmGhMjz/ekCJIBIkO0pfRgvJwzrW0XpdqVYl8olcEV+CMdxitNqf7BDMtRQOM4eonElaHwSIxWTQvhXsvJG+3YPb2jHR659eL+0tcVj/iH5sU5MRBOMlP5d6W8fTr1uPF31Z7YkxRmSzKl8KHMimBPCrVUI8mqTtQpDHkb5hIQYYocNM25/SP/QYBtsgbbj5gaUmdKaM71j04U212ozZZkhOIG0VdrdrGwWS2u2+1soFkVRaa/fAIUhpRNQQxV/u11cK1WUfjNjMAz7zwXbZDvjQ5KObthMYnEXWZawrR/hrkZuxPW48dScP4qCyualdOW2f8i/sQT133bI2lptgkni5u7aWrEs8WgcgnA/Avs3wIPFeppM3K5sra1tVb7A5LMqyXD4lud8WGcS6C03tlL77AZGRP3GhLzyhSFDDVceH3eBJB275cfQXIgtLZG2jTo5JN4ubtk6DGWWSMuTm7WKhIptM0VPMlhCKh60Dkqd4aAkQ1QVnqs56NJWLN28U9fp/APEprycRgRpl1g6GhlxNFK5JRi9aG5OSt+v265N6jbqtkz52IadRTdQXW+vlTZXmEQjvy7WbV+VtmwwUGEcHM8yKrUmSs4WBv35YJgA2dgZh88OYRxmTpZE+Q91hxuHx1WhtBaurUkb/s1Mbfp4TVQ3eAlxvK1r2LAtKOtPra1SjCE24bGeF+PrjIgYYsWtlZXiGsTTuyc1BARehhCPTSDdtSxUQVLvBJbDZzIEk9nKa7X7q7uZ4ml+StrQP3luhFqRJoFJtXRp7XelTOk33a8emK63x7x0/+vl5P36Sm0hXlo5kyEUaYwk3jECPzJBnlrkqNjKPUZWhucE0fJSiO3lxG34UT992527J6RippZOl+BHmfGPzyX6p/HRNpRlUrouEjzPnzoDM6nEW26r/1Sefxf844N+hnl3O3gYUPSRaGKQYULD1sQbWokZ6Q3dJwd/OkO9YT2quyDWQcXqztfruNXENb1n/U2N/rH0hgPDhp/+lts0bLAOXhC3BkyGwWEIO+9qiFrAZ25u0DrnGYZq7Ex4rPNv6m8YVsKGeu1Fob9m0Ocu1iWnvWUYNlz4SoMK2HDgEqjIi4EyX746Zqcu1MdO0Vfn2Iv1gV703E9nqL9kZqk+aHo3ejbt8HHNXcq39mo66O1r7z8jNHRdtub7Tv12wEnwscs/3YT6y472TeK4hhIseDyOO4STm6cocy9clMY8dzlPcfE+QB/4OTrKQQNXTxeH2QGP5Qqy4ZNWS878brjMODV2VVGGlBCIxNyAWCTgoOxthvSVuUtduKy1txg6ZtzdMLqzghBBnS04O9/bBTqBDa8MspQFnd9nscMZLr0LcwVFGYLJhL2Y22gMer1eo9E9E3DgLd704EC3LjVpwaqIodnn9QaNwTaMxljWbgl7jd6ggOOmHik7MDCcw2WGuKVqNHojFrvL2tvkLAF8Q2mGbCSG6MG9wm0bve6wg+LsyIbyYD++2QGt7KVmu8Nn9Boz3g7FoDtLWcLQNSbgeU/vvRqujlAwDgdZjeCExxex4Oa5dyp9wzVlGeJsrEXNPRNDpgFj+CxoLFJjl+euXL58RcZlwCIMog5DeBLGYy99wbUZajSeK+0OrV5Xr2j7GV66euXtgEsW7BoFGTpmjLKr+ZKBwL7PDQyN3mkLOCpuTtHdcI3hXTZ0BpqBDnCNo21DDd2LMdqs6WXI0ueAS6OcDXEHjCDgN/1cXizgGPW5gbF7j8ujSSXKjsvRW4OCDwTxjg3hMfiWuU5w5zQaDhh6EcMzkgV1zDAIDM+VLjSKMaQ4ahRijNedFOzoRjV2CqzhhZvNy8mQ5eTcQWnsiD6rsXcxtOCaE7QZas5CL8Mzm5zRSSkb2i0RsKA7aaG4Th5ciLjBa5MOSjOaBMTlzMihX78V8HcwPPP+f16GVD4GOcIpdPghw3EQWY2Q26iAG6w7ivbighMehE9hht3KSHMsjpRmGHDDMGyCBU+uJISRWUGg9DAMIoYaxRjCkLDbTzHUtESpkgy5JEoUOId3XUsemu49jSYAgfV9MQSVaBGEXoYC7JPBKckwAtd1CqymS0lzGmBoDFPviyHIagHfC0cie6MCy7YiGYVzjmwy0gbHKcfQ4oME7+yJgeAsKH+EhffDEDyRYyPuGBJPbucoxcqXtFNxJxKOLRVIKc0w2MdQtmHyPdkQUtCTmHEGSUSjd8bdlHOU3ZI9ANkoC0cYIUBbOYbTcF53HO/yUg2XRSIn8N68dBTsB6IIOsBlYgGIAZT9ecwrUwaAzuVwZRkaY8nuSCOraIilFPV+GLIoP3ndsRmoZ0AGx/LQSfAhyR90tsFCbFeSIQhKrsdLQYkHq3ImeQ8MhT2j8cDr3hMsQhZdCAY8Rz13I1mbhfAqx1KIRQozNIbNlOyoyJLLSFcHYRi+k+Fx8moxRLWFnNE07ZR2NkMfGn5ZjoM2eUQxBtqxiQrTANc+maw7FGSI/D8YdnCypsAp89eogkJWfStDr0+QV/hyHAX/tTx7Bk37cJDO0AfdZYvkCUN0BlBMXuM0yoSUXUhCJ/cLyMqgO2Is3j1WlGWIasOsBe6YdQRmUO1nDLyLIcT042mM2AtKkBmi2iTpiwWDTt/+6PGMT5cN4QxxN5C6iYYFShFurxeuxe2hW3hu73ZtZRnCtYNuZ3gvGUFFMIz5MBpRb2WICuUWwDtf2Fs2ND/xuVsBEZKdb9RBnbYhF0dpT84RYO84ShsBisq6wXHCUI9x74NhEM3OBFuZCKVcuDtworczBBbH8zTw9FuzGAexPXc75rfSWtKCI+nZPQ4h88a8MWPYIeddrommF15wdgf6dvugph61sLhdaRt6Z8Juo7fDsFUsIiX+VobGLnTmadr9UeqekRtE4ET2PoZCFVUucQ5FI0cV+rhRbki2zohcPskp7qVe50IWSSYZ3phvVMBlb3k7Q2fyBPFWpJFn4NzB6f39aafs+8Zph53qHYeUAFEFItnossXy3IdSf8RB4bgjInv3DHKJmbji43DGAbI37IwBfMlRy3GUfytD3zJ3jFa2kK3oDDgsqEYIOI1InewhedJtQ1zDyfkpNhPxQR16MAPSAtKLhptGvt2a8IuBgRVlGJwBUtxJlNe8m+GZGR+5gM+ikZ2Mk60CJHHIID0McS7rbo17FJBnjElB7oALTSe4qNs7AyfaF5S1ITCU77I9jfQvMwQj+hbs7b15zQIUZl5jsp8h4hLrhDavO9xW/TAWBc3o6Og+cm4Ymu+FoRzeulPShRnG2OPZHhQpYy3pcKp6ssCwlydm3bGmwLbSPLRnOQ3QRDHHC7L//TDsxcW9NNxzIiHplTPJ6RqfEwJhX3UmEhBOzc5xLIrrSUWVt4IMR3tPEIcyyZ08gyHFUoLFIQgcznFUbx+OQzLnfyvDINvTH+cQw3DXrL7spbg84I9FO949OHCNBU2Nuff+13kpBQy9oEt7x7FjBglACu/J+LI0t3QBZRuuMwclBFCEdUNG/DcxPO9sIsUl0dsrtpch1LpgQ6GX4WisD1A1UclgZwNlEpRz/i1eeqFZjG9RSdI3DkfdaBxymh6G8VhHsreACg02ebIPzB6L4+8rW/SCC6CS43wMNVmUx5Kc5iRbUFQT5bzeWCpXT71wB1gu2dG5B0jSZJWcL32rDbPIhi/OxxCSn3HmwHLC0E4JQdAnMa6fIWTJ3nfkN+0gvFsbYMNYJC6L4n8Lwzh6mRjmzsUQpLcXFIq5lQ0Qw4V95I/Tjt5soYmH9/fDXUiGszgXSKLfAPvNuIVT8u1am6Fcjve/wKM4QZ5GQatL4P5AsvQwrFq61pnIjwMFQVCUaNIcTWsn3UE0jLtrCyin4LT9QAnjeKNzRsUZ2vvXDqGLQAqACOmMQ+l9mqHPwR4DJTJ5HgOq2OcWdL+OeMSNJkRBdlLdDJdP8TsFlgUhoHiksXNjfaDNyDQxuQYIBwIvTjF0ZtuvuG/evJkHw+UdMygOQn25FwjsRdxovtfrRP7fXR9mAzcDb8HNFgJPlB+H7NzVXlwqUJTdEkDiGZlGDoA99WGwExPReho51bOoeu8cQcWwcUYWOV02NPYH0jPhdsPgV5ghzvX/DT8TYmi3JN3G9suEfobHCHYYalhfDMq7zmSB1+1rzSt1MzwfUBJVnmHfQVMBvRGyg4o6eezvYMhRjmbM3T7iNcYCjm6Glp+HIeXwQRZCb9fm+ldeFeToCtUMqr1biMhvudn+VV/uNkO0doNr+pD0csd8TY5qz/DKDCkh5j4/wg7lVipAWR0ftXN2Vz9aa95Q2nBw8dEWOKq9cm+0F3gnz0O0t1hw2GERKK4z9wkM51jKPnoBxBWLpWh+BlXWlP2MVZPtu0ZVzvFOmSFOaTSnWre1Gs62sg13/MKunS0orr/Lm1fTcJxCM1FXXDI1uOGz+J2g877Ebreb567+YMf7qtbutp1H1f5GT8hOX51znDyxdwMUH3RSYhW0SV6bPAJo/exB94rk410jepPekzvV+I1LmeWDIwb98C3tGZd4I1DXi/+9+DMwZdIPDw9fu3at/39zfjMMhmH9+Vu3T47+QNNFOiHolfiD5ehP3ptaJ4KffSsg5UMdDJzsGjaYDIb+xqY3AZ3bYACC6H8ee9eiy55rTyliQxUqVKhQoUKFChUqVKhQoUKFChUqVKhQoUKFChUqVKhQoUKFChUqVKhQoUKFChUqVKhQoUKFChUq/t/C/wD2YX8IOi1sHQAAAABJRU5ErkJggg==');
insert into partners(name, email, password, latitude, longitude, address, rating, image_url)
values ('Malocco Cafe Cascade', 'malocco@cafe.am', 'Password1$', 40.18909, 44.515018,
        '1 Tamanyan St', 4.7, 'https://media-cdn.tripadvisor.com/media/photo-s/04/13/c0/f7/malocco-cafe.jpg');
insert into partners(name, email, password, latitude, longitude, address, rating, image_url)
values ('The Green Bean', 'green@bean.am', 'Password1$', 40.198465, 44.515898, '10 Amiryan St',
        4.5, 'https://media-cdn.tripadvisor.com/media/photo-s/05/b0/5e/9e/the-green-bean.jpg');
insert into partners(name, email, password, latitude, longitude, address, rating, image_url)
values ('Crumbs', 'crumbs@bread.am', 'Password1$', 40.186371, 44.513629, '37 Mesrop Mashtots Ave',
        4.9, 'http://www.marogmarketing.com/site/images/clients/crumbs_logo_320_1472042715.jpg');
insert into partners(name, email, password, latitude, longitude, address, rating, image_url)
values ('Coffeeshop Company Yerevan', 'coffee@shop.am', 'Password1$', 40.179073, 44.510718, '5/4 Amiryan St',
        4.2,
        'https://upload.wikimedia.org/wikipedia/en/thumb/3/37/Coffeeshop_Company_logo.svg/1200px-Coffeeshop_Company_logo.svg.png');
insert into partners(name, email, password, latitude, longitude, address, rating, image_url)
values ('Van Gogh Art Cafe', 'van@gogh.am', 'Password1$', 40.185807, 44.51244, '31a Tumanyan St',
        4.6, 'https://www.armeniatourinfo.com/wp-content/uploads/2019/07/15354225_1376278979080330_2002953097_o.jpg');


insert into categories(type)
values ('italian');
insert into categories(type)
values ('mexican');
insert into categories(type)
values ('chinese');
insert into categories(type)
values ('armenian');
insert into categories(type)
values ('japanese');
insert into categories(type)
values ('russian');


insert into activity(type)
values ('with friends');
insert into activity(type)
values ('with family');
insert into activity(type)
values ('for couples');
insert into activity(type)
values ('birthday');
insert into activity(type)
values ('work & study');


insert into partners_categories(partner_id, type_id)
VALUES (1, 1);
insert into partners_categories(partner_id, type_id)
VALUES (1, 2);
insert into partners_categories(partner_id, type_id)
VALUES (1, 3);
insert into partners_categories(partner_id, type_id)
VALUES (2, 2);
insert into partners_categories(partner_id, type_id)
VALUES (3, 5);
insert into partners_categories(partner_id, type_id)
VALUES (4, 1), (4, 4);
insert into partners_categories(partner_id, type_id)
VALUES (5, 5);
insert into partners_categories(partner_id, type_id)
VALUES (6, 6);


insert into reviews(user_id, partner_id, rating, comment)
values (1, 1, 5, 'Everything was good, especially tequila');
insert into reviews(user_id, partner_id, rating, comment)
values (1, 2, 3, 'Should improve service');
insert into reviews(user_id, partner_id, rating, comment)
values (1, 3, 4, '');
insert into reviews(user_id, partner_id, rating, comment)
values (1, 4, 5, 'One of best cuisines in Yerevan');
insert into reviews(user_id, partner_id, rating, comment)
values (1, 6, 4, 'Art was good, rest was ok');




insert into partners_activities(partner_id, type_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);
insert into partners_activities(partner_id, type_id)
values (2, 1),
       (2, 2),
       (2, 3);
insert into partners_activities(partner_id, type_id)
values (3, 1),
       (3, 2),
       (3, 3);
insert into partners_activities(partner_id, type_id)
values (4, 1),
       (4, 2),
       (4, 3),
       (4, 5);
insert into partners_activities(partner_id, type_id)
values (5, 1),
       (5, 2),
       (5, 3),
       (5, 5);
insert into partners_activities(partner_id, type_id)
values (6, 1),
       (6, 2),
       (6, 3);


insert into partners_categories (partner_id, type_id) values (1, 2);